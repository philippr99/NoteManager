package net.teammagic.taskmanager;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML private TextField txt_username;
    @FXML private PasswordField txt_password;
    @FXML private Label lbl_loginerror;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("<initialisation>");
    }

    @FXML
    public void login_click(ActionEvent actionEvent) {
        if (!txt_username.getText().equals("user") || !txt_password.getText().equals("pass")) {
            lbl_loginerror.setVisible(true);
            txt_username.setText("");
            txt_password.setText("");
        }
        else {
            System.out.println("login valid");
            lbl_loginerror.setVisible(false);
        }
    }


}