package net.teammagic.taskmanager.model;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostTextFormatter {
    String title, category, details;

    public PostTextFormatter(String title, String category, String details) {
        this.title = title;
        this.category = category;
        this.details = details;
    }

    public static ObservableList<PostTextFormatter> getItems(String requestResult) {
        requestResult =  requestResult.replace("}\"", "},\"");  //delete after changing php script

        System.out.println(requestResult);
        Gson json = new Gson();

        TableContent ptf = json.fromJson(requestResult, TableContent.class);
        System.out.println(ptf);



        //String[] posts = requestResult.split("\"topic\":\"|\", \"category\":\"|\", \"post\":\"");
        //String[] posts = requestResult.split("\\{");

        ObservableList<PostTextFormatter> contents = FXCollections.observableArrayList();
//        contents.add(new PostTextFormatter("title", "cat", "detail"));
//        contents.add(new PostTextFormatter("title2", "cat2", "detail2"));
//        contents.add(new PostTextFormatter("title3", "cat3", "detail3"));
//        contents.add(new PostTextFormatter("title4", "cat4", "detail4"));

        return contents;
    }
}
