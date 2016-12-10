package net.teammagic.taskmanager.gui.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.teammagic.taskmanager.Main;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;

public class HomeController {

    private static Stage homeStage;

    public static void initHomeWindow(String session) {
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

        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), "NULL"));
        System.out.println(result);
    }
}