package sn.ssi.sigmap.repository;

import sn.ssi.sigmap.domain.SigRealisation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SigRealisation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SigRealisationRepository extends JpaRepository<SigRealisation, Long> {
}
