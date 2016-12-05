package net.teammagic.taskmanager.api;

/**
 * Created by s3now on 12/5/16.
 */
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

}
