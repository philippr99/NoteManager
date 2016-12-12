package net.teammagic.taskmanager.model;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

        TableContent[] ptf = json.fromJson(requestResult, TableContent[].class);
        ObservableList<PostTextFormatter> contents = FXCollections.observableArrayList();
        for(TableContent tbc : ptf)
        {
            contents.add(new PostTextFormatter(tbc.topic,tbc.category,tbc.post));
        }

        return contents;
    }
}
