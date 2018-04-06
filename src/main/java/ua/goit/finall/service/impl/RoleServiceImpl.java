package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.RoleRepository;
import ua.goit.finall.model.Role;
import ua.goit.finall.service.RoleService;

import java.util.List;

/**
 * The service's for Role, contains CRUD methods
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * Role repository's is called for use it's methods
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Get by id role.
     * @param id
     *
     * @return role
     */
    @Override
    public Role getById(Long id) {
        return roleRepository.findOne(id);
    }

    /**
     * Save role.
     * @param role
     */
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    /**
     * Delete role.
     * @param id
     */
    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    /**
     * Update role.
     * @param entity
     */
    @Override
    public Role update(Role entity) {
        return null;
    }

    /**
     * Get All Role role.
     *
     * @return all role
     */
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
