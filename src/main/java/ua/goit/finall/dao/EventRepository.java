package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}