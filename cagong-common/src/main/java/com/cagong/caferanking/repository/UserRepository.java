package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
