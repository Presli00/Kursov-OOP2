import entity.City;
import entity.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            Role customer=new Role();
            customer.setRoleId(1);
            customer.setRoleName("Customer");
            customer.setSubroleId(1);
            entityManager.persist(customer);
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("WarehouseAgentGUI.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Styles/SpravkiStyles.css").toExternalForm()); // ADD CSS TO SCENE
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/PR Warehouses.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
