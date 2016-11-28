package net.teammagic.taskmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    private TextField txt_username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("<initialisation>");
    }

    @FXML
    public void login_click(ActionEvent actionEvent) {
    }
}