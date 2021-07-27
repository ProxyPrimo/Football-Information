package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.PictureEntity;
import softuni.exam.domain.entities.TeamEntity;

@Repository
public interface TeamRepository
        extends JpaRepository<TeamEntity, Long> {

    TeamEntity findByNameAndPicture(String name, PictureEntity pictureEntity);
}
