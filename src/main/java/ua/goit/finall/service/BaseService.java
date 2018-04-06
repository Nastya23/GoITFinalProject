package ua.goit.finall.service;

import ua.goit.finall.model.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity>  {

    /**
     * Get all entities.
     * @return  List<T> Entities list.
    * */
    List<T> getAll();

    /**
     * Get entity where id.
     * @param id Entity id.
     * @return T Entity object.
     * */
    T getById(Long id);

    /**
     * Save entity.
     * @param entity Entity object.
     * */
    void save(T entity);

    /**
     * Delete entity where id.
     * @param id Entity id.
     * */
    void delete(Long id);

    /**
     * Update entity.
     * @param entity Entity object.
     * @return T entity object.
     * */
    T update(T entity);

}
