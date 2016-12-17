package net.teammagic.taskmanager.gui.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;
import net.teammagic.taskmanager.gui.WindowManager;
import net.teammagic.taskmanager.model.Data;
import net.teammagic.taskmanager.model.PostTextFormatter;

import java.net.URL;
import java.util.ResourceBundle;

import static net.teammagic.taskmanager.gui.WindowManager.initAddNoteWindow;
import static net.teammagic.taskmanager.model.PostTextFormatter.getItems;

public class HomeController implements Initializable {
    @FXML public BorderPane borderPane;
    @FXML public Button btn_logout;
    @FXML public Button btn_addNote;
    @FXML public TextArea txt_content;

    private TableRow<PostTextFormatter> selectedRow;
    private TableView<PostTextFormatter> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebApi api = new WebApi(Data.url + "backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), Data.sessionID));
        if (result.length() > 2) loadTable(result);
    }

    private void loadTable(String requestResult) {
        table = new TableView<>();
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<PostTextFormatter, String>("title"));

        TableColumn categoryCol = new TableColumn("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<PostTextFormatter, String>("category"));

        TableColumn detailsCol = new TableColumn("Details");
        detailsCol.setCellValueFactory(new PropertyValueFactory<PostTextFormatter, String>("details"));

        titleCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        categoryCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        detailsCol.prefWidthProperty().bind(table.widthProperty().multiply(0.496));

        table.setRowFactory(tv -> {
            TableRow<PostTextFormatter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    txt_content.setText(row.getItem().getDetails());
                    selectedRow = row;
                }
            });
            return row;
        });

        table.setItems(getItems(requestResult));
        table.getColumns().addAll(titleCol, categoryCol, detailsCol);
        borderPane.setCenter(table);
    }

    @FXML void openAddNote_click() {
        initAddNoteWindow(Data.sessionID);
        refresh_click();
    }

    @FXML void refresh_click() {
        txt_content.setText("");
        WebApi api = new WebApi(Data.url + "backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), Data.sessionID));
        if (result.length() > 2) loadTable(result);
        else System.out.println("no data in table!");
    }

    @FXML void delete_click(){
        if (selectedRow == null || selectedRow.getItem() == null) return;
        WebApi api = new WebApi(Data.url + "backend/deletePost.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), Data.sessionID), new PostArgument<>(ARGS.noteID.toString(), selectedRow.getItem().getNoteID()));
        if (result.startsWith("{\"result\" : \"success\"}")) System.out.println("Delete Success!");
        refresh_click();
    }

    @FXML void logout_click() {
        new WebApi(Data.url + "backend/logout.php");
        WindowManager.initLoginWindow(new Stage());
        WindowManager.homeStage.close();
    }
}