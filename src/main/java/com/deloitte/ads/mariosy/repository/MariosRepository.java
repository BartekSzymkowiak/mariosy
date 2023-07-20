package com.deloitte.ads.mariosy.repository;

import com.deloitte.ads.mariosy.entity.MariosEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MariosRepository extends CrudRepository<MariosEntity,Long> {

    List<MariosEntity> findMariosEntitiesByCreator_Id(Long id);

    List<MariosEntity> findMariosEntitiesByCreator_ExternalId(UUID externalId);

    Optional<MariosEntity> findMariosEntityByExternalId(UUID externalId);

    @Query("SELECT marios FROM MariosEntity marios")
    List<MariosEntity> search();
}


