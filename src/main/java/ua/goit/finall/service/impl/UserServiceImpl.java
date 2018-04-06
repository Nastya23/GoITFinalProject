package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.RoleRepository;
import ua.goit.finall.dao.UserRepository;
import ua.goit.finall.model.User;
import ua.goit.finall.service.UserService;

import java.util.List;

/**
 * The service's for User, contains CRUD methods
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * User repository's is called for use it's methods
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Role repository's is called for use it's methods
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Password encoder is called for use it's methods
     */
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /**
     * Get by id user.
     * @param id
     *
     * @return user
     */
    @Override
    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    /**
     * Save user.
     * @param user
     */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Delete user.
     * @param id
     */
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    /**
     * Update user.
     * @param entity
     */
    @Override
    public User update(User entity) {
        return null;
    }

    /**
     * Get All User user.
     *
     * @return all user
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Find user by name.
     * @param user name
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
