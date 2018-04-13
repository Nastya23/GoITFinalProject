package ua.goit.finall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.EventTypeRepository;
import ua.goit.finall.service.EventTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class EventTypeServiceImplTest {

    @MockBean
    EventTypeRepository eventTypeRepository;

    @Autowired
    EventTypeService eventTypeService;

    private EventType eventType;


    @Before
    public void setup() {
        eventType = new EventType("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        eventType.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(eventTypeRepository.save(eventType)).thenReturn(eventType);
        eventTypeService.save(eventType);

        verify(eventTypeRepository.save(any(EventType.class)));

    }

    @Test
    @Transactional
    public void update() {
        eventTypeService.save(eventType);
        eventType.setName("Wrong");
        eventTypeService.update(eventType);
        eventTypeRepository.save(eventType);
        Assert.assertEquals(eventType.getName() , "Wrong");

    }

    @Test
    public void delete() {
        eventTypeService.save(eventType);
        eventTypeRepository.save(eventType);
        Long id = eventType.getId();
        eventTypeService.delete(id);
        verify(eventTypeRepository, times(1)).delete(eventType);
    }
}
