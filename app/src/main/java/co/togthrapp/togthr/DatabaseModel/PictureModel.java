package co.togthrapp.togthr.DatabaseModel;

import java.io.File;
import java.util.List;

/**
 * Created by sabri on 16/12/17.
 */

public class PictureModel extends BaseTimelineItem {
    private File pictureFile;

    public PictureModel() {
        //Pojo
    }

    public PictureModel(String author, String type, File pictureFile, List<String> tags) {
        this.author = author;
        this.type = type;
        this.pictureFile = pictureFile;
        this.tags = tags;
    }

    public File getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(File pictureFile) {
        this.pictureFile = pictureFile;
    }
}
