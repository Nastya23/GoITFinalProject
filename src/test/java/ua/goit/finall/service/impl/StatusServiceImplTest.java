package ua.goit.finall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.StatusRepository;
import ua.goit.finall.service.StatusService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class StatusServiceImplTest {

    @MockBean
    StatusRepository statusRepository;

    @Autowired
    StatusService statusService;

    private Status status;


    @Before
    public void setup() {
        status = new Status("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        status.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(statusRepository.save(status)).thenReturn(status);
        statusService.save(status);

        verify(statusRepository.save(any(Status.class)));

    }

    @Test
    @Transactional
    public void update() {
        statusService.save(status);
        status.setName("Wrong");
        statusService.update(status);
        statusRepository.save(status);
        Assert.assertEquals(status.getName() , "Wrong");

    }

    @Test
    public void delete() {
        statusService.save(status);
        statusRepository.save(status);
        Long id = status.getId();
        statusService.delete(id);
        verify(statusRepository, times(1)).delete(status);
    }
}
