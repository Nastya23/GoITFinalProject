package ua.goit.finall.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.DepartmentRepository;
import ua.goit.finall.model.Department;
import ua.goit.finall.service.DepartmentService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class DepartmentServiceImplTest {

    @MockBean
    DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    private Department department;


    @Before
    public void setup() {
        department = new Department("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        department.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(departmentRepository.save(department)).thenReturn(department);
        departmentService.save(department);

        verify(departmentRepository.save(any(Department.class)));

    }

    @Test
    @Transactional
    public void update() {
        departmentService.save(department);
        department.setName("Wrong");
        departmentService.update(department);
        departmentRepository.save(department);
        Assert.assertEquals(department.getName() , "Wrong");

    }

    @Test
    public void deleteDepartment() {
        departmentService.save(department);
        departmentRepository.save(department);
        Long id = department.getId();
        departmentService.delete(id);
        verify(departmentRepository, times(1)).delete(department);
    }
}
