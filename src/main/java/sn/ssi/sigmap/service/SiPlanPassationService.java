package sn.ssi.sigmap.service;

import sn.ssi.sigmap.domain.SiPlanPassation;
import sn.ssi.sigmap.repository.AuditRepository;
import sn.ssi.sigmap.repository.SiPlanPassationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SiPlanPassation}.
 */
@Service
@Transactional
public class SiPlanPassationService {

    private final Logger log = LoggerFactory.getLogger(SiPlanPassationService.class);

    private final SiPlanPassationRepository siPlanPassationRepository;

    private final AuditRepository auditRepository;

    public SiPlanPassationService(SiPlanPassationRepository siPlanPassationRepository, AuditRepository auditRepository) {
        this.siPlanPassationRepository = siPlanPassationRepository;
        this.auditRepository = auditRepository;
    }

    /**
     * Save a siPlanPassation.
     *
     * @param siPlanPassation the entity to save.
     * @return the persisted entity.
     */
    public SiPlanPassation save(SiPlanPassation siPlanPassation) {
        log.debug("Request to save SiPlanPassation : {}", siPlanPassation);
        return siPlanPassationRepository.save(siPlanPassation);
    }

    /**
     * Get all the siPlanPassations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SiPlanPassation> findAll(Pageable pageable) {
        log.debug("Request to get all SiPlanPassations");
        return siPlanPassationRepository.findAll(pageable);
    }


    /**
     * Get one siPlanPassation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SiPlanPassation> findOne(Long id) {
        log.debug("Request to get SiPlanPassation : {}", id);
        return siPlanPassationRepository.findById(id);
    }

    /**
     * Delete the siPlanPassation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SiPlanPassation : {}", id);
        siPlanPassationRepository.deleteById(id);
    }
}
