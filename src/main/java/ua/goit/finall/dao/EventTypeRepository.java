package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.EventType;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

}
