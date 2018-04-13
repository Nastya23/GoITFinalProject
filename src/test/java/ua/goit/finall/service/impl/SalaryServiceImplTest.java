package ua.goit.finall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.SalaryRepository;
import ua.goit.finall.service.SalaryService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class SalaryServiceImplTest {

    @MockBean
    SalaryRepository salaryRepository;

    @Autowired
    SalaryService salaryService;

    private Salary salary;


    @Before
    public void setup() {
        salary = new Salary("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        salary.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(salaryRepository.save(salary)).thenReturn(salary);
        salaryService.save(salary);

        verify(salaryRepository.save(any(Salary.class)));

    }

    @Test
    @Transactional
    public void update() {
        salaryService.save(salary);
        salary.setName("Wrong");
        salaryService.update(salary);
        salaryRepository.save(salary);
        Assert.assertEquals(salary.getName() , "Wrong");

    }

    @Test
    public void delete() {
        salaryService.save(salary);
        salaryRepository.save(salary);
        Long id = salary.getId();
        salaryService.delete(id);
        verify(salaryRepository, times(1)).delete(salary);
    }
}
