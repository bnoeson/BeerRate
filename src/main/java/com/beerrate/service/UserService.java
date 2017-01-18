package com.beerrate.service;

import com.beerrate.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
	User findById(Long userid);
}
