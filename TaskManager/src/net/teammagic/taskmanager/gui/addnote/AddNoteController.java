package net.teammagic.taskmanager.gui.addnote;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.teammagic.taskmanager.Main;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;
import net.teammagic.taskmanager.gui.home.HomeController;

public class AddNoteController {
    @FXML
    private TextArea txt_title;
    @FXML
    private TextArea txt_text;
    @FXML
    private TextArea txt_category;
    @FXML
    private Label lbl_error;

    private static Stage addNoteStage;
    private static String sessionID;

    public static void initHomeWindow(String token) {
        sessionID = token;
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gui/addnote/addnote.fxml"));
        } catch (Exception e) {
            System.out.println("Resource /addnote.fxml not found or damaged! exiting...");
            System.exit(-1);
        }
        addNoteStage = new Stage(StageStyle.DECORATED);
        addNoteStage.initModality(Modality.WINDOW_MODAL);
        addNoteStage.initOwner(HomeController.getHomeStage());
        addNoteStage.setTitle("Task Manager - Add Note");
        addNoteStage.setScene(new Scene(root));
        addNoteStage.setResizable(false);
        addNoteStage.show();
    }

    @FXML
    void clear_click() {
        txt_title.setText("");
        txt_category.setText("");
        txt_text.setText("");
    }

    @FXML
    void submit_click() {
        if (txt_title.getText().length() < 3 || txt_text.getText().length() < 3) lbl_error.setVisible(true);
        else {
            WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/addPost.php");

            String text = txt_text.getText().replace("\n", " ");
            System.out.println(text);

            String result = api.postRequest(new PostArgument<>(ARGS.topic.toString(), txt_title.getText()), new PostArgument<>(ARGS.category.toString(), txt_category.getText()),
                    new PostArgument<>(ARGS.postText.toString(), text), new PostArgument<>(ARGS.sessionID.toString(), sessionID));

            System.out.println("Add Note Message: " + result);
        }
    }
}
