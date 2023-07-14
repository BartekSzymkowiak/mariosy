package com.deloitte.ads.mariosy.repository;

import com.deloitte.ads.mariosy.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional <UserEntity> findUserByEmail(String str);

    Set<UserEntity> findUsersByIdIn(Set<Long> ids);

    Optional <UserEntity> findUserById(Long id);

    @Query("SELECT u FROM user_account u WHERE u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%"
            + " OR u.email LIKE %?1%"
            )
    Set<UserEntity> searchUserEntities(String searchKeyword);
}
