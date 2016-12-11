package net.teammagic.taskmanager.gui.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.teammagic.taskmanager.Main;
import net.teammagic.taskmanager.model.PostTextFormatter;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;
import net.teammagic.taskmanager.gui.addnote.AddNoteController;
import net.teammagic.taskmanager.gui.login.LoginController;

import static net.teammagic.taskmanager.model.PostTextFormatter.getItems;

public class HomeController {

    private static Stage homeStage;
    private static String sessionID;
    private static TableView<PostTextFormatter> table;

    public static Stage getHomeStage() {
        return homeStage;
    }

    @FXML
    static BorderPane borderPane;
    @FXML
    Button btn_logout;
    @FXML
    Button btn_addNote;
    @FXML
    static TextArea txt_content;

    public static void initHomeWindow(String token) {
        sessionID = token;
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gui/home/home.fxml"));
        } catch (Exception e) {
            System.out.println("Resource /home.fxml not found or damaged! exiting...");
            System.exit(-1);
        }
        homeStage = new Stage(StageStyle.DECORATED);
        homeStage.setTitle("Task Manager");
        homeStage.setScene(new Scene(root));
        homeStage.setResizable(false);
        homeStage.show();

        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), sessionID));
        System.out.println(result);
        loadTable(result);
    }


    private static void loadTable(String requestResult) {
        table = new TableView();
        TableColumn<PostTextFormatter, String> titleCol = new TableColumn<>("Title");
        titleCol.setMinWidth(80);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<PostTextFormatter, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setMinWidth(100);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<PostTextFormatter, String> detailsCol = new TableColumn<>("Details");
        detailsCol.setMinWidth(120);
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));

        table.setItems(getItems(requestResult));
        table.getColumns().addAll(titleCol, categoryCol, detailsCol);

        borderPane.setCenter(table);
    }

    @FXML
    void openAddNote_click() {
        AddNoteController.initHomeWindow(sessionID);
    }

    @FXML
    void refresh_click() {
        WebApi api = new WebApi("http://teammagic722.bplaced.net/backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), sessionID));
        System.out.println(result);
    }

    @FXML
    void logout_click() {
        new WebApi("http://teammagic722.bplaced.net/backend/logout.php");
        LoginController.initLoginWindow(new Stage());
        homeStage.close();
    }
}