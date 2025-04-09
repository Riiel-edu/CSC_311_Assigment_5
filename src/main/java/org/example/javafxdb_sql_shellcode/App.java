package org.example.javafxdb_sql_shellcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("splash-view.fxml"));

        AnchorPane root = new AnchorPane();
        root.getChildren().add(fxmlLoader.load());

        scene = new Scene(root, 675, 400);
        stage.setScene(scene);
        splashSetup(root, stage);
        stage.show();
    }

    static void splashSetup(AnchorPane root, Stage stage) {
        Button login = new Button();
        login.setLayoutX(240); login.setLayoutY(320); login.prefWidth(200); login.prefHeight(50);
        login.setText("LOGIN");
        root.getChildren().add(login);
        login.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
            try {
                Stage loginStage = new Stage();
                AnchorPane loginRoot = new AnchorPane();
                loginRoot.getChildren().add(fxmlLoader.load());

                Scene scene = new Scene(loginRoot, 675, 400);
                loginStage.setScene(scene);
                loginStage.setResizable(true);
                //loginSetup(loginRoot, loginStage);

                stage.close();
                loginStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    static void loginSetup(AnchorPane root, Stage stage) {

        Button login = new Button();
        login.setLayoutX(250); login.setLayoutY(350); login.maxWidth(200); login.maxHeight(50);
        login.setText("LOGIN");
        root.getChildren().add(login);
        login.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register-view.fxml"));
            try {
                Stage loginStage = new Stage();
                AnchorPane landingRoot = new AnchorPane();
                landingRoot.getChildren().add(fxmlLoader.load());

                Scene scene = new Scene(landingRoot, 675, 400);
                loginStage.setScene(scene);
                loginStage.setResizable(true);

                stage.close();
                loginStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button register = new Button();
        register.setLayoutX(250); register.setLayoutY(350); register.maxWidth(200); register.maxHeight(50);
        register.setText("LOGIN");
        root.getChildren().add(register);
        register.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register-view.fxml"));
            try {
                Stage loginStage = new Stage();
                AnchorPane landingRoot = new AnchorPane();
                landingRoot.getChildren().add(fxmlLoader.load());

                Scene scene = new Scene(landingRoot, 675, 400);
                loginStage.setScene(scene);
                loginStage.setResizable(true);

                stage.close();
                loginStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);

            switch (input) {
                case 'g':
                     launch(); //GUI
                    break;

                case 'c':
                    cdbop.connectToDatabase(); //Your existing method
                    break;
                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;

                case 'i':
                    System.out.print("Enter Name: ");
                    String name = scan.next();
                    System.out.print("Enter Email: ");
                    String email = scan.next();
                    System.out.print("Enter Phone: ");
                    String phone = scan.next();
                    System.out.print("Enter Address: ");
                    String address = scan.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scan.next();
                    cdbop.insertUser(name, email, phone, address, password); //Your insertUser method
                    break;
                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserByName(queryName); //Your queryUserByName method
                    break;
                case 'e':
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');

        scan.close();

       
    }




}
