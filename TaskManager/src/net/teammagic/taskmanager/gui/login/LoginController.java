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
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;
import net.teammagic.taskmanager.gui.WindowManager;
import net.teammagic.taskmanager.gui.home.HomeController;

public class LoginController {

    @FXML
    private TextField txt_username;
    @FXML
    public PasswordField txt_password;
    @FXML
    private Label lbl_loginerror;

    @FXML
    public void login_click() {
        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/register_login.php");
        String result = api.postRequest(new PostArgument<>(ARGS.isLogin.toString(), 1), new PostArgument<>(ARGS.username.toString(), txt_username.getText()),
                new PostArgument<>(ARGS.password.toString(), txt_password.getText()));
        String token = result.substring(result.lastIndexOf(": \"", result.length() - 2) + 3, result.length() - 2);
        if (result.startsWith("{\"error\": \"none\"")) {
            WindowManager.initHomeWindow(token);
            WindowManager.loginStage.close();
        } else if (result.startsWith("{\"error\": \"wrong_pw\"")) {
            showError("Login failed! Wrong password.");
        } else if (result.startsWith("'{\"error\": \"user_not_exists\"")) {
            showError("Login failed! User does not exist.");
        } else showError("Login failed! Unknown username or error.");
    }

    @FXML
    public void register_click() {
        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/register_login.php"); //"http://teammagic722.bplaced.net/backend/register_login.php"
        String result = api.postRequest(new PostArgument<>(ARGS.isLogin.toString(), 0), new PostArgument<>(ARGS.username.toString(), txt_username.getText()),
                new PostArgument<>(ARGS.password.toString(), txt_password.getText()));

        if (result.startsWith("{\"error\": \"name_exists\"")) showError("Registration failed! Username already exists");
        else showError("Registration completed!");
    }

    public void enterPWfield() {
        lbl_loginerror.setVisible(false);
    }

    private void showError(String message) {
        lbl_loginerror.setText(message);
        lbl_loginerror.setVisible(true);
        txt_password.setText("");
    }
}