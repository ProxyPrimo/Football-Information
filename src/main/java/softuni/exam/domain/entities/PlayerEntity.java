package softuni.exam.domain.entities;

import softuni.exam.domain.entities.enumerations.PositionValue;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "players")
public class PlayerEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private Integer number;
    private PositionValue position;
    private BigDecimal salary;
    private PictureEntity picture;
    private TeamEntity team;

    public PlayerEntity() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Enumerated
    public PositionValue getPosition() {
        return position;
    }

    public void setPosition(PositionValue position) {
        this.position = position;
    }

    @Column(nullable = false)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @ManyToOne
    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }

    @ManyToOne
    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}
