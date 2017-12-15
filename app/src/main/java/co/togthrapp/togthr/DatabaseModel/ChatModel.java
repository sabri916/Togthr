package co.togthrapp.togthr.DatabaseModel;

import java.util.List;

/**
 * Created by sabri on 15/12/17.
 */

public class ChatModel {

    private String author;
    private String type;
    private String text;
    private List<String> tags;

    public ChatModel() {
        //empty because POJO
    }

    public ChatModel(String author, String type, String text, List<String> tags) {
        this.author = author;
        this.type = type;
        this.text = text;
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
