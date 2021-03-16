package sn.ssi.sigmap.repository;

import sn.ssi.sigmap.domain.SiPlanPassation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SiPlanPassation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiPlanPassationRepository extends JpaRepository<SiPlanPassation, Long> {
}
