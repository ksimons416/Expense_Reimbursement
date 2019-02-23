package com.spark.ex.data;

import com.spark.ex.pojos.User;

public interface UserDao {

    User getByEmail(String email);
}
