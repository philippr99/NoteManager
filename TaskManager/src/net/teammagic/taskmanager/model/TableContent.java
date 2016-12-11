package net.teammagic.taskmanager.model;

public class TableContent {
    private String topic;
    private String category;
    private String post;

    public TableContent(String topic, String category, String post) {
        this.topic = topic;
        this.category = category;
        this.post = post;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String toString(){
        return "Topic: " + topic + " Category: " + category + " Post: " + post;
    }
}
