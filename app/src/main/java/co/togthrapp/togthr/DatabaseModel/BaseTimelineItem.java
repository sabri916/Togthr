package co.togthrapp.togthr.DatabaseModel;

import java.util.List;

/**
 * Created by sabri on 15/12/17.
 */

public class BaseTimelineItem {

    protected String author;
    protected String type;
    protected List<String> tags;

    public BaseTimelineItem() {
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


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
