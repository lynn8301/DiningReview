package com.diningReview.DiningReview.repository;
import org.springframework.data.repository.CrudRepository;
import com.diningReview.DiningReview.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    boolean existsByPostcode(String postcode);
    boolean existsByName(String name);
    Optional<Restaurant> findByPostcode(String postcode);
    
}