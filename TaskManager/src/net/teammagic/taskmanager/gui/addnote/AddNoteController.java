package net.teammagic.taskmanager.gui.addnote;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;
import net.teammagic.taskmanager.gui.WindowManager;
import net.teammagic.taskmanager.model.Data;

public class AddNoteController {
    @FXML private TextField txt_title;
    @FXML private TextField txt_category;
    @FXML private TextArea txt_text;
    @FXML private Label lbl_error;

    @FXML void clear_click() {
        txt_title.setText("");
        txt_category.setText("");
        txt_text.setText("");
    }

    @FXML void submit_click() {
        if (txt_title.getText().length() < 3 || txt_text.getText().length() < 3) lbl_error.setVisible(true);
        else {
            WebApi api = new WebApi(Data.url + "backend/addPost.php");

            String title = txt_title.getText(), category = txt_category.getText(), text = txt_text.getText().replace("\n", " ");

            String result = api.postRequest(new PostArgument<>(ARGS.topic.toString(), title), new PostArgument<>(ARGS.category.toString(), category),
                    new PostArgument<>(ARGS.postText.toString(), text), new PostArgument<>(ARGS.sessionID.toString(), Data.sessionID));

            if (result.startsWith("{\"result\" : \"success\"}")) WindowManager.addNoteSuccess();
        }
    }
}
