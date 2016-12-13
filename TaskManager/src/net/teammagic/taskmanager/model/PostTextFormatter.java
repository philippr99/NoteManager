package net.teammagic.taskmanager.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PostTextFormatter {
    public String title, category, details;

    public PostTextFormatter(String title, String category, String details) {
        this.title = title;
        this.category = category;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static ObservableList<PostTextFormatter> getItems(String requestResult) {
        Gson json = new Gson();

        TableContent[] ptf = new TableContent[0];
        try {
            ptf = json.fromJson(requestResult, TableContent[].class);
        } catch (JsonSyntaxException jse){
            infoBox("No Tasks exist!", "Information", null);
        }
        ObservableList<PostTextFormatter> contents = FXCollections.observableArrayList();

        for (TableContent tbc : ptf) contents.add(new PostTextFormatter(tbc.topic, tbc.category, tbc.post));

        return contents;
    }
    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}
