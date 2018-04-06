package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
