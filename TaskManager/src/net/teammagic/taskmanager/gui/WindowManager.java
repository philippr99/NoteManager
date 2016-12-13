package net.teammagic.taskmanager.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.teammagic.taskmanager.Main;
import net.teammagic.taskmanager.model.Data;

import java.io.IOException;

public final class WindowManager {

    public static Stage homeStage;
    public static Stage addNoteStage;
    public static Stage loginStage;

    public static void initAddNoteWindow(String token) {
        Data.sessionID = token;
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gui/addnote/addnote.fxml"));
        } catch (Exception e) {
            System.out.println("Resource /addnote.fxml not found or damaged! exiting...");
            System.exit(-1);
        }
        addNoteStage = new Stage(StageStyle.DECORATED);
        addNoteStage.initModality(Modality.WINDOW_MODAL);
        addNoteStage.initOwner(WindowManager.homeStage);
        addNoteStage.setTitle("Task Manager - Add Note");
        addNoteStage.setScene(new Scene(root));
        addNoteStage.setResizable(false);
        addNoteStage.show();
    }

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

    public static void initHomeWindow(String token) {
        Data.sessionID = token;
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gui/home/home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Resource /home.fxml not found or damaged! exiting...");
            System.exit(-1);
        }

        homeStage = new Stage(StageStyle.DECORATED);

        homeStage.setTitle("Task Manager");
        homeStage.setScene(new Scene(root));
        homeStage.setResizable(false);
        homeStage.show();

    }
}
