package net.teammagic.taskmanager.gui.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.teammagic.taskmanager.Main;
import net.teammagic.taskmanager.gui.home.HomeController;

public class LoginController {

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label lbl_loginerror;

    private static Stage loginStage;

    public static void initLoginWindow(Stage rootStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gui/login/login.fxml"));
        } catch (Exception e) {
            System.out.println("Resource /login.fxml not found or damaged! exiting...");
            System.exit(-1);
        }

        loginStage = rootStage;
        loginStage.setTitle("Task Manger Login");
        loginStage.setScene(new Scene(root));
        loginStage.setResizable(false);
        loginStage.show();
    }

    @FXML
    public void login_click() {
        if (!txt_username.getText().equals("user") || !txt_password.getText().equals("pass")) {
            lbl_loginerror.setVisible(true);
            txt_username.setText("");
            txt_password.setText("");
        } else {
            loginStage.close();
            HomeController.initHomeWindow();
        }
    }
}