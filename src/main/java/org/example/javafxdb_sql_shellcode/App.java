package org.example.javafxdb_sql_shellcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;
    static String curr_email;
    static String curr_password;

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
        login.setLayoutX(240); login.setLayoutY(320); login.setPrefWidth(200); login.setPrefHeight(50);
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
                loginStage.setResizable(false);
                loginSetup(loginRoot, loginStage);

                stage.close();
                loginStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Sets up the login page of the program
     * @param root the AnchorPane which the login page is located
     * @param stage the Stage which the login page is set on
     */
    static void loginSetup(AnchorPane root, Stage stage) {
        TextField emailField = new TextField();
        emailField.setPrefWidth(300);
        emailField.setPrefHeight(30);
        emailField.setLayoutX(355);
        emailField.setLayoutY(121);
        emailField.setPromptText("EMAIL");
        root.getChildren().add(emailField);

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(30);
        passwordField.setLayoutX(355);
        passwordField.setLayoutY(220);
        passwordField.setPromptText("PASSWORD");
        root.getChildren().add(passwordField);

        Button login = new Button();
        login.setLayoutX(515); login.setLayoutY(337); login.setPrefWidth(140); login.setPrefHeight(40);
        login.setText("LOGIN");
        root.getChildren().add(login);
        login.setOnAction(e -> {
            curr_email= emailField.getText();
            curr_password = passwordField.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("landing-view.fxml"));
            try {
                Stage landingStage = new Stage();
                AnchorPane landingRoot = new AnchorPane();
                landingRoot.getChildren().add(fxmlLoader.load());

                Scene scene = new Scene(landingRoot, 850, 560);
                landingStage.setScene(scene);
                landingStage.setResizable(true);
                scene.getStylesheets().add(Objects.requireNonNull(App.class.getResource("lightlanding.css")).toExternalForm());
                landingSetup(landingRoot, scene);
                landingStage.setResizable(false);

                stage.close();
                landingStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button register = new Button();
        register.setLayoutX(355); register.setLayoutY(337); register.setPrefWidth(140); register.setPrefHeight(40);
        register.setText("REGISTER");
        root.getChildren().add(register);
        register.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register-view.fxml"));
            try {
                Stage registerStage = new Stage();
                AnchorPane registerRoot = new AnchorPane();
                registerRoot.getChildren().add(fxmlLoader.load());

                Scene scene = new Scene(registerRoot, 675, 400);
                registerStage.setScene(scene);
                registerStage.setResizable(false);
                registerSetup(registerRoot, registerStage);

                stage.close();
                registerStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    static void registerSetup(AnchorPane root, Stage stage) {
        TextField nameField = new TextField();
        nameField.setPrefWidth(300);
        nameField.setPrefHeight(30);
        nameField.setLayoutX(355);
        nameField.setLayoutY(73);
        nameField.setPromptText("USERNAME");
        root.getChildren().add(nameField);

        TextField emailField = new TextField();
        emailField.setPrefWidth(300);
        emailField.setPrefHeight(30);
        emailField.setLayoutX(355);
        emailField.setLayoutY(128);
        emailField.setPromptText("EMAIL");
        root.getChildren().add(emailField);

        TextField passwordField = new TextField();
        passwordField.setPrefWidth(300);
        passwordField.setPrefHeight(30);
        passwordField.setLayoutX(355);
        passwordField.setLayoutY(181);
        passwordField.setPromptText("PASSWORD");
        root.getChildren().add(passwordField);

        TextField phoneField = new TextField();
        phoneField.setPrefWidth(300);
        phoneField.setPrefHeight(30);
        phoneField.setLayoutX(355);
        phoneField.setLayoutY(237);
        phoneField.setPromptText("PHONE #");
        root.getChildren().add(phoneField);

        TextField addressField = new TextField();
        addressField.setPrefWidth(300);
        addressField.setPrefHeight(30);
        addressField.setLayoutX(355);
        addressField.setLayoutY(288);
        addressField.setPromptText("ADDRESS");
        root.getChildren().add(addressField);

        Button registerButton = new Button();
        registerButton.setPrefWidth(300);registerButton.setPrefHeight(46);registerButton.setLayoutX(355);registerButton.setLayoutY(339);
        registerButton.setText("Register");
        root.getChildren().add(registerButton);
        registerButton.setOnAction(e -> {
            cdbop = new ConnDbOps();

            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            cdbop.insertUser(name, email, phone, address, password);

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
            try {
                Stage loginStage = new Stage();
                AnchorPane loginRoot = new AnchorPane();
                loginRoot.getChildren().add(fxmlLoader.load());

                Scene scene = new Scene(loginRoot, 675, 400);
                loginStage.setScene(scene);
                loginStage.setResizable(false);
                loginSetup(loginRoot, loginStage);

                stage.close();
                loginStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static final String dark = Objects.requireNonNull(App.class.getResource("darklanding.css")).toExternalForm();
    private static final String light = Objects.requireNonNull(App.class.getResource("lightlanding.css")).toExternalForm();

    static void landingSetup(AnchorPane root, Scene scene) {
        AtomicBoolean lighttheme = new AtomicBoolean(true);
        Button colorSwap = new Button();
        colorSwap.setLayoutX(70); colorSwap.setLayoutY(400); colorSwap.setPrefWidth(75); colorSwap.setPrefHeight(75);
        colorSwap.setText("Theme");
        root.getChildren().add(colorSwap);
        colorSwap.setOnAction(e -> {
            if(lighttheme.get()) {
                scene.getStylesheets().remove(light);
                scene.getStylesheets().add(dark);
                lighttheme.set(false);
            }
            else {
                scene.getStylesheets().remove(dark);
                scene.getStylesheets().add(light);
                lighttheme.set(true);
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

    public static String getCurrPassword() {
        return curr_password;
    }

    public static String getCurrEmail() {
        return curr_email;
    }
}
