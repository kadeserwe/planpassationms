package sn.ssi.sigmap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.sigmap.domain.PlanPassation;

import java.time.LocalDate;

/**
 * Spring Data  repository for the PlanPassation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanPassationRepository extends JpaRepository<PlanPassation, Long> {
    Page<PlanPassation> findAllByDateCreationBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable);
}
