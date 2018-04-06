package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.SalaryRepository;
import ua.goit.finall.model.Salary;
import ua.goit.finall.service.SalaryService;

import java.util.List;

/**
 * The service's for Salary, contains CRUD methods
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    /**
     * Salary repository's is called for use it's methods
     */
    @Autowired
    SalaryRepository salaryRepository;

    /**
     * Get by id salary.
     * @param id
     *
     * @return salary
     */
    @Override
    public Salary getById(Long id) {
        return salaryRepository.findOne(id);
    }

    /**
     * Save salary.
     * @param salary
     */
    @Override
    public void save(Salary salary) {
        salaryRepository.save(salary);
    }

    /**
     * Delete salary.
     * @param id
     */
    @Override
    public void delete(Long id) {
        salaryRepository.delete(id);
    }

    /**
     * Update salary.
     * @param entity
     */
    @Override
    public Salary update(Salary entity) {
        return null;
    }

    /**
     * Get All Salary salary.
     *
     * @return all salary
     */
    @Override
    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    /**
     * Get personal salary.
     *@param id employee
     *@param from year
     *@param from month
     *@param to year
     *@param to month
     *
     * @return salary
     */
    @Override
    public List<Salary> getPersonalSalaries(Long employeeId, Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) {
        return salaryRepository.getPersonalSalaries(employeeId, fromYear, fromMonth, toYear, toMonth);
    }
}
