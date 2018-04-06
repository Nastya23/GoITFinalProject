package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.StatusRepository;
import ua.goit.finall.model.Status;
import ua.goit.finall.service.StatusService;

import java.util.List;

/**
 * The service's for Status, contains CRUD methods
 */
@Service
public class StatusServiceImpl implements StatusService {

    /**
     * Status repository's is called for use it's methods
     */
    @Autowired
    StatusRepository statusRepository;

    /**
     * Get by id status.
     * @param id
     *
     * @return status
     */
    @Override
    public Status getById(Long id) {
        return statusRepository.findOne(id);
    }

    /**
     * Save status.
     * @param status
     */
    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    /**
     * Delete status.
     * @param id
     */
    @Override
    public void delete(Long id) {
        statusRepository.delete(id);
    }

    /**
     * Update status.
     * @param entity
     */
    @Override
    public Status update(Status entity) {
        return null;
    }

    /**
     * Get All Status status.
     *
     * @return all status
     */
    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }
}
