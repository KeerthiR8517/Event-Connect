package com.vedapixel.event.Connect.dao;

import com.vedapixel.event.Connect.entity.User;
import com.vedapixel.event.Connect.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        String sql = "SELECT id, username, password FROM event.users " +
                "WHERE username = ? AND password = ?";

        return jdbcTemplate.query(sql,
                new Object[]{username, password},
                rs -> {
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                    return null;
                });
    }
}


