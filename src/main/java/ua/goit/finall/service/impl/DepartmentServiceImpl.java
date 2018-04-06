package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.DepartmentRepository;
import ua.goit.finall.model.Department;
import ua.goit.finall.service.DepartmentService;

import java.util.List;

/**
 * The service's for Department, contains CRUD methods
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    /**
     * Department repository's is called for use it's methods
     */
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Get by id department.
     * @param id
     *
     * @return department
     */
    @Override
    public Department getById(Long id) {
        return departmentRepository.findOne(id);
    }

    /**
     * Save department.
     * @param department
     */
    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    /**
     * Delete department.
     * @param id
     */
    @Override
    public void delete(Long id) {
        departmentRepository.delete(id);
    }

    /**
     * Update department.
     * @param entity
     */
    @Override
    public Department update(Department entity) {
        return null;
    }

    /**
     * Get All Department department.
     *
     * @return all department
     */
    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

}
