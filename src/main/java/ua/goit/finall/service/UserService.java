package ua.goit.finall.service;

import ua.goit.finall.model.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    User findUserByUsername(String username);

}
