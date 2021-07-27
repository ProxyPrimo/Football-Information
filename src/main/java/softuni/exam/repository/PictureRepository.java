package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.PictureEntity;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
}
