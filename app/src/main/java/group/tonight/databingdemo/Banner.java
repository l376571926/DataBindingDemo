package group.tonight.databingdemo;

import java.io.Serializable;

public class Banner implements Serializable {
    private static final long serialVersionUID = -1114832405519424096L;
    private String imageUrl;

    public Banner() {
    }

    public Banner(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
