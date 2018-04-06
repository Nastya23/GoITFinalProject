package ua.goit.finall.service;

import ua.goit.finall.model.Salary;

import java.util.List;

public interface SalaryService extends BaseService<Salary> {

    List<Salary> getPersonalSalaries(Long employeeId, Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth);

}
