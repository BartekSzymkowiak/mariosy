package com.deloitte.ads.mariosy.repository;

import com.deloitte.ads.mariosy.entity.MariosEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MariosRepository extends CrudRepository<MariosEntity,Long> {

    @Query("select marios from MariosEntity marios where marios.creator.id =:id")
    Set<MariosEntity> findMariosEntitiesByCreatorId(@Param("id") Long id);



}


