package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

}
