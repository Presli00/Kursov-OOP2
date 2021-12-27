package KursovProektOOP2.controllers.Admin;

import KursovProektOOP2.controllers.Admin.AccountInfo;
import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Rating;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.data.repository.UserRepository;
import KursovProektOOP2.util.Panes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminAccountViewer {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();

    public final UserRepository repository = UserRepository.getInstance();
    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();
    public final AgentRepository agentRepository = AgentRepository.getInstance();
    List users;
    List owners;
    List agents;



    @FXML
    public void initialize() throws IOException {
        getAllUsers();
        new Thread(() -> getAllOwners()).start();
        new Thread(() -> getAllAgents()).start();
        for(int i = 0; i < users.size(); i++){ //map info to labels and add to vbox
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AdminViews/AccountInfo.fxml"));
            AnchorPane user = loader.load();
            AccountInfo controller = loader.getController();
            // Set data in the controller
            controller.idText.setText(String.valueOf(((User)users.get(i)).getUserId()));
            controller.usernameText.setText(((User) users.get(i)).getUsername());
            controller.roleText.setText(((User) users.get(i)).getRoleId().getRoleName());

            int finalI = i;
            controller.moreInfoButton.setOnAction(e->{
                if(controller.additionalInfoVbox.isVisible()){
                    Platform.runLater(()-> {
                        controller.additionalInfoVbox.getChildren().clear();
                        controller.additionalInfoVbox.setVisible(false);
                    });
                }
                HBox names = new HBox(); // Names
                Text firstName = new Text(((User) users.get(finalI)).getFirstName());
                firstName.setFont(Font.font("Arial", 20));
                Text lastName = new Text(((User) users.get(finalI)).getLastName());
                lastName.setFont(Font.font("Arial", 20));
                names.setSpacing(5);
                names.getChildren().addAll(firstName, lastName);
                Text emailText = new Text(((User) users.get(finalI)).geteMail()); //Email
                emailText.setFont(Font.font("Arial", 18));
                Text phoneText = new Text(((User) users.get(finalI)).getPhone()); // Phone number
                phoneText.setFont(Font.font("Arial", 18));
                Text createdAtText = new Text("Creation Date: " + ((User) users.get(finalI)).getCreatedDate().toString()); // Creation Date
                createdAtText.setFont(Font.font("Arial", 18));
                Text lastUpdateAtText = new Text(); // Update Date
                lastUpdateAtText.setFont(Font.font("Arial", 18));
                if(((User) users.get(finalI)).getUpdatedDate() == null){
                    lastUpdateAtText.setText("Last Update: Never");
                }else{
                    lastUpdateAtText.setText("Last Update: " + ((User) users.get(finalI)).getUpdatedDate().toString());
                }

                controller.additionalInfoVbox.getChildren().addAll(names, emailText, createdAtText, lastUpdateAtText); // Add to general info to VBox

                if(((User) users.get(finalI)).getRoleId().getRoleId() == 2 ){ // warehouses amount
                    for(int j = 0; j < owners.size(); j++){
                        if(String.valueOf(((User)users.get(finalI)).getUserId()).equals(String.valueOf(((Owner)owners.get(j)).getUserId().getUserId())) ){
                            Text warehouses = new Text("Warehouses: " + ((Owner) owners.get(j)).getWarehousesAmount());
                            warehouses.setFont(Font.font("Arial", 18));
                            controller.additionalInfoVbox.getChildren().add(warehouses);
                        }
                    }
                }
                if(((User) users.get(finalI)).getRoleId().getRoleId() == 3 ){
                    for(int j = 0; j < agents.size(); j++){
                        if(String.valueOf(((User)users.get(finalI)).getUserId()).equals(String.valueOf(((Agent)agents.get(j)).getIdFromUser().getUserId())) ){
                            Text rating = new Text("Rated: " + Panes.getRating((List<Rating>) ((Agent) agents.get(j)).getReceivedRatings()));
                            rating.setFont(Font.font("Arial", 18));
                            Text deals = new Text("Successful Deals: " + ((Agent) agents.get(j)).getDealAmount());
                            deals.setFont(Font.font("Arial", 18));
                            controller.additionalInfoVbox.getChildren().addAll(rating, deals);
                        }
                    }
                }


                controller.additionalInfoVbox.setVisible(true);
            });

            Vbox.getChildren().add(user);
        }

        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });

        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });

    }

    public void addUser() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AdminViews/RegistrationForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.setResizable(false);
        stage.show();
    }

    public void getAllUsers(){
        users = repository.getAll(); //get all users from database
    }
    public void getAllOwners(){
        owners = ownerRepository.getAll();
    }
    public void getAllAgents(){
        agents = agentRepository.getAll();
    }
}
