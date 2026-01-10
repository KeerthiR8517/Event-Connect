package com.vedapixel.event.Connect.service;

import com.vedapixel.event.Connect.Security.JwtUtil;
import com.vedapixel.event.Connect.entity.User;
import com.vedapixel.event.Connect.interfaces.AuthService;
import com.vedapixel.event.Connect.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) {

        User user = userDao.findByUsernameAndPassword(username, password);

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
