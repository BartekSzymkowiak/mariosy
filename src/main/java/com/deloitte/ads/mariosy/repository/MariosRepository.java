package com.deloitte.ads.mariosy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MariosRepository extends CrudRepository<Marios,Long> {

    Set<Marios> findByCreatorId(Integer creatorId);
}
