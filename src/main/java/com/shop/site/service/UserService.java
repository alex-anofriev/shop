package com.shop.site.service;

import com.shop.site.model.User;
import java.util.Optional;

public interface UserService extends CommonMethods<User>{
    Optional<User> findByEmail(String email);
}
