package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.EventTypeRepository;
import ua.goit.finall.model.EventType;
import ua.goit.finall.service.EventTypeService;

import java.util.List;

/**
 * The service's for Event type, contains CRUD methods
 */
@Service
public class EventTypeServiceImpl implements EventTypeService {

    /**
     * Event type repository's is called for use it's methods
     */
    @Autowired
    EventTypeRepository eventTypeRepository;

    /**
     * Get by id event type.
     * @param id
     *
     * @return event type
     */
    @Override
    public EventType getById(Long id) {
        return eventTypeRepository.findOne(id);
    }

    /**
     * Save event type.
     * @param event type
     */
    @Override
    public void save(EventType eventType) {
        eventTypeRepository.save(eventType);
    }

    /**
     * Delete event type.
     * @param id
     */
    @Override
    public void delete(Long id) {
        eventTypeRepository.delete(id);
    }

    /**
     * Update event type.
     * @param entity
     */
    @Override
    public EventType update(EventType entity) {
        return null;
    }

    /**
     * Get All Event type event type.
     *
     * @return all event type
     */
    @Override
    public List<EventType> getAll() {
        return eventTypeRepository.findAll();
    }
}
