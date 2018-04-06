package ua.goit.finall.service.impl;

import com.itextpdf.text.DocumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.EmployeeRepository;
import ua.goit.finall.model.Employee;
import ua.goit.finall.service.EmployeeService;
import ua.goit.finall.service.PdfService;

import javax.mail.internet.MimeMessage;
import java.io.OutputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class EmployeeServiceImplTest {

    /**
     * The service's layer object
     */
    @MockBean
    private EmployeeRepository employeeRepository;

    /**
     * The service's layer object
     */
    @MockBean
    private PdfService pdfService;

    /**
     * The service's layer object
     */
    @MockBean
    private JavaMailSender mailSender;

    /**
     * The service's layer object
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * The service's layer object
     */
    @Autowired
    private TestEntityManager entityManager;

    private Employee employee;

    @Test
    public void calculateSalary() throws DocumentException {
        employeeService.calculateSalary(1, 1);
        verify(employeeRepository).findAllWithDeps();
        //verify(employeeRepository).save(any(Employee.class));
        verify(pdfService).createEmployeeReport(any(Employee.class), any(OutputStream.class));
        verify(mailSender).send(any(MimeMessage.class));
    }

    @Test
    public void findByName_thenReturnEmployee() {
        Employee nastya = new Employee("Nastya");
        entityManager.persist(nastya);
        entityManager.flush();

        Employee found = employeeRepository.findEmployeeByName(nastya.getName());

        assertThat(found.getName()).isEqualTo(nastya.getName());
    }

    @Before
    public void setup() {
        employee = new Employee("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        employee.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.save(employee);

        verify(employeeRepository.save(any(Employee.class)));

    }

    @Test
    @Transactional
    public void update() {
        employeeService.save(employee);
        employee.setName("Wrong");
        employeeService.update(employee);
        employeeRepository.save(employee);
        Assert.assertEquals(employee.getName() , "Wrong");

    }

    @Test
    public void delete() {
        employeeService.save(employee);
        employeeRepository.save(employee);
        Long id = employee.getId();
        employeeService.delete(id);
        verify(employeeRepository, times(1)).delete(employee);
    }
}