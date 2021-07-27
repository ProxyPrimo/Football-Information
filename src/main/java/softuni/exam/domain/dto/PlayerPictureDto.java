package softuni.exam.domain.dto;

import com.google.gson.annotations.Expose;

public class PlayerPictureDto {

    @Expose
    private String url;

    public PlayerPictureDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
