package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.data.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
            controller.idLabel.setText(String.valueOf(((User)users.get(i)).getUserId()));
            controller.usernameLabel.setText(((User) users.get(i)).getUsername());
            controller.roleLabel.setText(((User) users.get(i)).getRoleId().getRoleName());
            if(((User) users.get(i)).getRoleId().getRoleId() == 2 ){
                controller.WarehousesOrRatingLabel.setText("Warehouses:");
                for(int j = 0; j < owners.size(); j++){
                    if(String.valueOf(((User)users.get(i)).getUserId()).equals(String.valueOf(((Owner)owners.get(j)).getUserId().getUserId())) ){
                        controller.WarehousesOrRatingNumLabel.setText(String.valueOf(((Owner)owners.get(j)).getWarehousesAmount()));
                    }
                }

            }
            if(((User) users.get(i)).getRoleId().getRoleId() == 3 ){
                controller.WarehousesOrRatingLabel.setText("Rating:");
                controller.successfulDealsLabel.setText("Successful Deals:");
                for(int j = 0; j < agents.size(); j++){
                    if(String.valueOf(((User)users.get(i)).getUserId()).equals(String.valueOf(((Agent)agents.get(j)).getIdFromUser().getUserId())) ){
                        controller.WarehousesOrRatingNumLabel.setText(String.valueOf(((Agent)agents.get(j)).getRating()));
                        controller.successfulDealsNumLabel.setText(String.valueOf(((Agent)agents.get(j)).getDealAmount()));
                    }
                }
            }
            controller.firstnameLabel.setText(((User) users.get(i)).getFirstName());
            controller.lastnameLabel.setText(((User) users.get(i)).getLastName());
            controller.emailLabel.setText(((User) users.get(i)).geteMail());
            controller.phoneLabel.setText(((User) users.get(i)).getPhone());
            controller.createdatLabel.setText(((User) users.get(i)).getCreatedDate().toString());
            if(((User) users.get(i)).getUpdatedDate() == null){
                controller.lastupdatedatLabel.setText("Never");
            }else{
                controller.lastupdatedatLabel.setText(((User) users.get(i)).getUpdatedDate().toString());
            }
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
