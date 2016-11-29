package net.teammagic.taskmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui/login/login.fxml"));
        loginStage.setTitle("Task Manger Login");
        loginStage.setScene(new Scene(root, 430, 300));
        loginStage.setResizable(false);
        loginStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
