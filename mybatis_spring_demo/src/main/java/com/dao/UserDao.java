package com.dao;

import com.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qinglin
 * @create 2020-09-06 12:50
 */
public interface UserDao {

    public void save(User user);
}
