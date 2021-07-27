package softuni.exam.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamRootDto {

    @XmlElement
    private List<TeamDto> team;

    public TeamRootDto() {
    }

    public List<TeamDto> getTeam() {
        return team;
    }

    public void setTeam(List<TeamDto> team) {
        this.team = team;
    }
}
