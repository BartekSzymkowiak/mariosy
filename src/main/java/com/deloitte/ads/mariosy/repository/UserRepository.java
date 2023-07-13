package com.deloitte.ads.mariosy.repository;

import com.deloitte.ads.mariosy.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    Optional <UserEntity> findUserByEmail(String str);

    Set<UserEntity> findUsersByIdIn(Set<Long> ids);

    Optional <UserEntity> findUserById(Long id);
}
