package ua.goit.finall.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.EventRepository;
import ua.goit.finall.model.Event;
import ua.goit.finall.service.EventService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class EventServiceImplTest {

    @MockBean
    EventRepository eventRepository;

    @Autowired
    EventService eventService;

    private Event event;

    @Before
    public void setup() {
        event = new Event();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Transactional
    public void create() {
        when(eventRepository.save(event)).thenReturn(event);
        eventService.save(event);

        verify(eventRepository.save(any(Event.class)));

    }

    @Test
    @Transactional
    public void update() {
        eventService.save(event);
        eventService.update(event);
        eventRepository.save(event);
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void deleteDepartment() {
        eventService.save(event);
        eventRepository.save(event);
        Long id = event.getId();
        eventService.delete(id);
        verify(eventRepository, times(1)).delete(event);
    }
}
