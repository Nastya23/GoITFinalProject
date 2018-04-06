package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.PositionRepository;
import ua.goit.finall.model.Position;
import ua.goit.finall.service.PositionService;

import java.util.List;

/**
 * The service's for Position, contains CRUD methods
 */
@Service
public class PositionServiceImpl implements PositionService {

    /**
     * Position repository's is called for use it's methods
     */
    @Autowired
    PositionRepository positionRepository;

    /**
     * Get by id position.
     * @param id
     *
     * @return position
     */
    @Override
    public Position getById(Long id) {
        return positionRepository.findOne(id);
    }

    /**
     * Save position.
     * @param position
     */
    @Override
    public void save(Position position) {
        positionRepository.save(position);
    }

    /**
     * Delete position.
     * @param id
     */
    @Override
    public void delete(Long id) {
        positionRepository.delete(id);
    }

    /**
     * Update position.
     * @param entity
     */
    @Override
    public Position update(Position entity) {
        return null;
    }

    /**
     * Get All Position position.
     *
     * @return all position
     */
    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }
}
