package net.teammagic.taskmanager.gui.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.teammagic.taskmanager.Main;

public class HomeController {

    private static Stage homeStage;

    public static void initHomeWindow() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gui/home/home.fxml"));
        } catch (Exception e) {
            System.out.println("Resource /login.fxml not found or damaged! exiting...");
            System.exit(-1);
        }
        homeStage = new Stage(StageStyle.DECORATED);
        homeStage.setTitle("Task Manager");
        homeStage.setScene(new Scene(root));
        homeStage.setResizable(false);
        homeStage.show();
    }
}