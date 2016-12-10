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
        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/register_login.php");
        String result = api.postRequest(new PostArgument<>(ARGS.isLogin.toString(), 1), new PostArgument<>(ARGS.username.toString(), txt_username.getText()),
                new PostArgument<>(ARGS.password.toString(), txt_password.getText()));

        String token = result.substring(result.lastIndexOf(": \"", result.length() - 2) + 3, result.length() - 2);

        if (result.indexOf("{\"error\": \"none\",") == 0) {
            loginStage.close();
            HomeController.initHomeWindow(token);
        } else {
            lbl_loginerror.setVisible(true);
            txt_password.setText("");
        }
    }

    @FXML
    public void register_click() {
        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/register_login.php"); //"http://teammagic722.bplaced.net/backend/register_login.php"
        String result = api.postRequest(new PostArgument<>(ARGS.isLogin.toString(), 0), new PostArgument<>(ARGS.username.toString(), txt_username.getText()),
                new PostArgument<>(ARGS.password.toString(), txt_password.getText()));
    }

    public void enterPWfield() {
        lbl_loginerror.setVisible(false);
    }
}