package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Salary;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("select s from Salary s where s.employee.id = :employeeId and s.year >= :fromYear and s.year <= :toYear and s.month >= :fromMonth and s.month <= :toMonth")
    List<Salary> getPersonalSalaries(@Param("employeeId") Long employeeId, @Param("fromYear") Integer fromYear, @Param("fromMonth") Integer fromMonth,
                                     @Param("toYear") Integer toYear, @Param("toMonth") Integer toMonth);
}
