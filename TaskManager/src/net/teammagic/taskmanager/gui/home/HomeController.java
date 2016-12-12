package net.teammagic.taskmanager.gui.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.teammagic.taskmanager.gui.WindowManager;
import net.teammagic.taskmanager.model.Data;
import net.teammagic.taskmanager.model.PostTextFormatter;
import net.teammagic.taskmanager.api.PostArgument;
import net.teammagic.taskmanager.api.PostArgument.ARGS;
import net.teammagic.taskmanager.api.WebApi;

import java.net.URL;
import java.util.ResourceBundle;

import static net.teammagic.taskmanager.model.PostTextFormatter.getItems;

public class HomeController implements Initializable{
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button btn_logout;
    @FXML
    public Button btn_addNote;
    @FXML
    public TextArea txt_content;

    private TableView<PostTextFormatter> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebApi api = new WebApi("http://"+Data.url+"/backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), Data.sessionID));
        System.out.println(result);
        loadTable(result);
    }

    private void loadTable(String requestResult) {
        table = new TableView<PostTextFormatter>();
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(80);
        titleCol.setCellValueFactory(new PropertyValueFactory<PostTextFormatter,String>("title"));

        TableColumn categoryCol = new TableColumn("Category");
        categoryCol.setMinWidth(100);
        categoryCol.setCellValueFactory(new PropertyValueFactory<PostTextFormatter, String>("category"));

        TableColumn detailsCol = new TableColumn("Details");
        detailsCol.setMinWidth(120);
        detailsCol.setCellValueFactory(new PropertyValueFactory<PostTextFormatter, String>("details"));

        ObservableList<PostTextFormatter> posts = getItems(requestResult);
        for(PostTextFormatter post : posts)
        {
            System.out.println("Detail: "+post.details);
        }

        table.setItems(posts);
        table.getColumns().addAll(titleCol, categoryCol, detailsCol);
        borderPane.setCenter(table);
    }


    @FXML
    void openAddNote_click() {
        WindowManager.initAddNoteWindow(Data.sessionID);
    }

    @FXML
    void refresh_click() {
        WebApi api = new WebApi("http://"+Data.url+"/backend/listTasks.php");
        String result = api.postRequest(new PostArgument<>(ARGS.sessionID.toString(), Data.sessionID));
        System.out.println(result);
    }

    @FXML
    void logout_click() {
        new WebApi("http://"+Data.url +"/backend/logout.php");
        WindowManager.initLoginWindow(new Stage());
        WindowManager.homeStage.close();
    }


}