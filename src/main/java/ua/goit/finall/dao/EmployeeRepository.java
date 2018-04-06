package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Employee;
import ua.goit.finall.model.User;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByName(String name);

    Employee findEmployeeByUser(User user);

    @Query("select e from Employee e join fetch e.eventList ev")
    List<Employee> findAllWithDeps();
}