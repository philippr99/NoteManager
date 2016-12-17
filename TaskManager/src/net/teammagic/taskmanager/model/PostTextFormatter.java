package net.teammagic.taskmanager.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostTextFormatter {
    private String title, category, details, noteID;

    public PostTextFormatter(String title, String category, String details, String noteID) {
        this.title = title;
        this.category = category;
        this.details = details;
        this.noteID = noteID;
    }

    public static ObservableList<PostTextFormatter> getItems(String requestResult) {
        Gson json = new Gson();

        TableContent[] ptf;
        try {
            ptf = json.fromJson(requestResult, TableContent[].class);
        } catch (JsonSyntaxException jse) {
            System.out.println("Json Syntax Exception: " + jse.getMessage());
            return null;
        }

        ObservableList<PostTextFormatter> contents = FXCollections.observableArrayList();
        for (TableContent tbc : ptf) contents.add(new PostTextFormatter(tbc.topic, tbc.category, tbc.post, tbc.noteID));
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDetails() {
        return details;
    }

    public String getNoteID() {
        return noteID;
    }
}
