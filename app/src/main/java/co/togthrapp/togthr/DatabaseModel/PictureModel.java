package co.togthrapp.togthr.DatabaseModel;

import java.util.List;

/**
 * Created by sabri on 16/12/17.
 */

public class PictureModel extends BaseTimelineItem {
    private String pictureId;

    public PictureModel() {
        //Pojo
    }

    public PictureModel(String author, String type, String pictureId, List<String> tags) {
        this.author = author;
        this.type = type;
        this.pictureId = pictureId;
        this.tags = tags;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
}
