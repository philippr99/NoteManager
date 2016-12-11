package net.teammagic.taskmanager.api;

public class PostArgument<ValueType> {

    private String key;
    private ValueType value;

    public PostArgument(String key, ValueType value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }

    public enum ARGS {
        isLogin, username, password, sessionID, topic, postText, category
    }
}
