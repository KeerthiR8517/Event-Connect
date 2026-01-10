package com.vedapixel.event.Connect.interfaces;


import com.vedapixel.event.Connect.entity.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);
}

