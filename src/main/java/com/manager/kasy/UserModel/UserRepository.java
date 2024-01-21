package com.manager.kasy.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Long countById(Integer id);
}
