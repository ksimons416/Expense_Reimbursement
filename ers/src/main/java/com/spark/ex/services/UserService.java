package com.spark.ex.services;

import com.spark.ex.data.UserDao;
import com.spark.ex.data.UserDaoImpl;
import com.spark.ex.pojos.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public User authUser(User u) {
        User dbUser = this.userDao.getByEmail(u.getEmail());

        if (dbUser != null) {
            if (dbUser.getPassword().equals(u.getPassword())) {
                dbUser.setPassword("");
            } else {
                dbUser = null;
            }
        }

        return dbUser;
    }

}
