package com.diningReview.DiningReview.repository;


import org.springframework.data.repository.CrudRepository;

import com.diningReview.DiningReview.model.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String userNmae);
} 