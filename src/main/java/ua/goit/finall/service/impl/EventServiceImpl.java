package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.EventRepository;
import ua.goit.finall.model.Event;
import ua.goit.finall.service.EventService;

import java.util.List;

/**
 * The service's for Event, contains CRUD methods
 */
@Service
public class EventServiceImpl implements EventService {

    /**
     * Event repository's is called for use it's methods
     */
    @Autowired
    EventRepository eventRepository;

    /**
     * Get by id event.
     * @param id
     *
     * @return event
     */
    @Override
    public Event getById(Long id) {
        return eventRepository.findOne(id);
    }

    /**
     * Save event.
     * @param event
     */
    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    /**
     * Delete event.
     * @param id
     */
    @Override
    public void delete(Long id) {
        eventRepository.delete(id);
    }

    /**
     * Update event.
     * @param entity
     */
    @Override
    public Event update(Event entity) {
        return null;
    }

    /**
     * Get All Event event.
     *
     * @return all event
     */
    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }
}
