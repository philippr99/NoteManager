package net.teammagic.taskmanager.gui.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;
import net.teammagic.taskmanager.gui.WindowManager;
import net.teammagic.taskmanager.model.Data;

public class LoginController {

    @FXML
    private TextField txt_username;
    @FXML
    public PasswordField txt_password;
    @FXML
    private Label lbl_loginerror;

    @FXML
    public void login_click() {
        WebApi api = new WebApi("http://" + Data.url + "/backend/register_login.php");
        String result = api.postRequest(new PostArgument<>(ARGS.isLogin.toString(), 1), new PostArgument<>(ARGS.username.toString(), txt_username.getText()),
                new PostArgument<>(ARGS.password.toString(), txt_password.getText()));
        String token = result.substring(result.lastIndexOf(": \"", result.length() - 2) + 3, result.length() - 2);
        if (result.startsWith("{\"error\": \"none\"")) {
            WindowManager.initHomeWindow(token);
            WindowManager.loginStage.close();
        } else if (result.startsWith("{\"error\": \"wrong_pw\"")) {
            showMessage("Login failed! Wrong password.");
        } else if (result.startsWith("'{\"error\": \"user_not_exists\"")) {
            showMessage("Login failed! User does not exist.");
        } else showMessage("Login failed! Unknown username or error.");
    }

    @FXML
    public void register_click() {
        WebApi api = new WebApi("http://" + Data.url + "/backend/register_login.php");
        String result = api.postRequest(new PostArgument<>(ARGS.isLogin.toString(), 0), new PostArgument<>(ARGS.username.toString(), txt_username.getText()),
                new PostArgument<>(ARGS.password.toString(), txt_password.getText()));
        if (result == null) {
            showMessage("Wrong Path/Fatal Error!");
            return;
        }
        if (result.startsWith("{\"error\": \"name_exists\"")) showMessage("Registration failed! Username already exists");
        else showMessage("Registration completed!");
    }

    public void enterPWfield() {
        lbl_loginerror.setVisible(false);
    }

    private void showMessage(String message) {
        lbl_loginerror.setText(message);
        lbl_loginerror.setVisible(true);
        txt_password.setText("");
    }
}