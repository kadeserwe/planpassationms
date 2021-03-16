package sn.ssi.sigmap.repository;

import sn.ssi.sigmap.domain.ConfGenSequence;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConfGenSequence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfGenSequenceRepository extends JpaRepository<ConfGenSequence, Long> {
    ConfGenSequence findByTableName(String tableName);

    @Modifying
    @Query("update ConfGenSequence set currentValue = (currentValue + 1) where tableName = ?1")
    Integer incrementByTableName(String tableName);
}
