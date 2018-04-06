package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
