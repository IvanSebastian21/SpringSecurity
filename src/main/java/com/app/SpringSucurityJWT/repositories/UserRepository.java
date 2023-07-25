package com.app.SpringSucurityJWT.repositories;

import com.app.SpringSucurityJWT.models.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

//    @Query("select myUser from UserEntity myUser where myUser.username = ?1")
//    Optional<UserEntity> getUsernameDataBase(String username);

}
