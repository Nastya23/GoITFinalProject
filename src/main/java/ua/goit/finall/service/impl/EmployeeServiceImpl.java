package ua.goit.finall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.EmployeeRepository;
import ua.goit.finall.model.*;
import ua.goit.finall.service.EmployeeService;
import ua.goit.finall.service.PdfService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The service's for Employee, contains CRUD methods
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class.getName());
    private final String TO_ADDRESS = "degtjarova@gmail.com";

    /**
     * Employee repository's is called for use it's methods
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Pdf service is called for use it's methods
     */
    @Autowired
    private PdfService pdfService;

    /**
     * JavaMailSender is called for use it's methods
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Get by id employee.
     * @param id
     *
     * @return employee
     */
    @Override
    public Employee getById(Long id) {
        return employeeRepository.findOne(id);
    }

    /**
     * Save employee.
     * @param employee
     */
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Delete employee.
     * @param id
     */
    @Override
    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    /**
     * Update employee.
     * @param entity
     */
    @Override
    public Employee update(Employee entity) {
        return null;
    }

    /**
     * Get All Employee employee.
     *
     * @return all employee
     */
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    /**
     * Find employee by name.
     * @param name
     *
     * @return employee by name
     */
    @Override
    public Employee findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    /**
     * Find employee by user.
     * @param user
     */
    @Override
    public Employee findEmployeeByUser(User user) {
        return employeeRepository.findEmployeeByUser(user);
    }

    /**
     * Calculate salary.
     * @param month
     * @param year
     */
    @Override
    public void calculateSalary(Integer month, Integer year) {
        for (Employee employee : employeeRepository.findAllWithDeps()) {
            double salary = getSalaryInRange(month, year, employee);

            List<Salary> salariesInDate = employee.getSalary().stream()
                    .filter(s -> s.getMonth().equals(month) && s.getYear().equals(year))
                    .collect(Collectors.toList());

            if (salariesInDate.isEmpty()) {
                employee.getSalary().add(new Salary(employee, month, year, BigDecimal.valueOf(salary)));
            } else {
                salariesInDate.forEach(s -> s.setSalarySum(new BigDecimal(salary)));
            }

            employeeRepository.save(employee);

            try {
                File file = new File("report.pdf");
                FileOutputStream reportStream = new FileOutputStream(file);
                pdfService.createEmployeeReport(employee, reportStream);

                sendMessageWithAttachment(TO_ADDRESS, "report", "report", file.getAbsolutePath());
            } catch (Exception e) {
                logger.error("Report creation error", e);
                e.printStackTrace();
            }

        }
    }

    /**
     * Send message with attachment.
     * @param to
     * @param subject
     * @param text
     */
    private void sendMessageWithAttachment(String to, String subject, String text,
                                           String pathToAttachment) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        mailSender.send(message);
    }

    /**
     * Get salary in range.
     * @param month
     * @param year
     * @param employee
     *
     * @return sum of salary
     */
    private double getSalaryInRange(Integer month, Integer year, Employee employee) {
        double salarySum = 0;
        double hourSalary = employee.getPosition().getHourSalary().doubleValue();
        for (Event event : employee.getEventList()) {
            if (event.getType().getType().equals(EventType.Types.WORK.getName()) && isDateInRange(event.getEventDate(), month, year)) {
                salarySum += event.getType().getRate() * hourSalary;
            }
        }
        return salarySum;
    }

    /**
     * Is date in range.
     * @param event of date
     * @param month
     * @param year
     *
     * return event of year & month
     */
    private boolean isDateInRange(Date eventDate, Integer month, Integer year) {
        int eventYear = LocalDateTime.ofInstant(eventDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        int eventMonth = LocalDateTime.ofInstant(eventDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getMonth().getValue();
        return year == eventYear && month == eventMonth;
    }
}