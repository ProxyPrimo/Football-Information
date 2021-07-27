package softuni.exam.domain.dto;

import com.google.gson.annotations.Expose;

public class PlayerTeamDto {
    @Expose
    private String name;
    @Expose
    private PlayerPictureDto picture;

    public PlayerTeamDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PlayerPictureDto picture) {
        this.picture = picture;
    }
}
