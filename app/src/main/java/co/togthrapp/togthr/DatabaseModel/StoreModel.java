package co.togthrapp.togthr.DatabaseModel;

/**
 * Created by sabri on 16/12/17.
 */

public class StoreModel {

    private String title;
    private String offerText;
    private int ImageId;

    public StoreModel(String title, String offerText, int ImageId) {
        this.title = title;
        this.offerText = offerText;
        this.ImageId = ImageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        this.ImageId = imageId;
    }
}
