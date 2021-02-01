package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.domain.PlanPassation;
import sn.ssi.sigmap.repository.PlanPassationRepository;
import sn.ssi.sigmap.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sn.ssi.sigmap.domain.PlanPassation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PlanPassationResource {

    private final Logger log = LoggerFactory.getLogger(PlanPassationResource.class);

    private static final String ENTITY_NAME = "planpassationmsPlanPassation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanPassationRepository planPassationRepository;

    public PlanPassationResource(PlanPassationRepository planPassationRepository) {
        this.planPassationRepository = planPassationRepository;
    }

    /**
     * {@code POST  /plan-passations} : Create a new planPassation.
     *
     * @param planPassation the planPassation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planPassation, or with status {@code 400 (Bad Request)} if the planPassation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plan-passations")
    public ResponseEntity<PlanPassation> createPlanPassation(@Valid @RequestBody PlanPassation planPassation) throws URISyntaxException {
        log.debug("REST request to save PlanPassation : {}", planPassation);
        if (planPassation.getId() != null) {
            throw new BadRequestAlertException("A new planPassation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanPassation result = planPassationRepository.save(planPassation);
        return ResponseEntity.created(new URI("/api/plan-passations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plan-passations} : Updates an existing planPassation.
     *
     * @param planPassation the planPassation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planPassation,
     * or with status {@code 400 (Bad Request)} if the planPassation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planPassation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plan-passations")
    public ResponseEntity<PlanPassation> updatePlanPassation(@Valid @RequestBody PlanPassation planPassation) throws URISyntaxException {
        log.debug("REST request to update PlanPassation : {}", planPassation);
        if (planPassation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanPassation result = planPassationRepository.save(planPassation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planPassation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /plan-passations} : get all the planPassations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planPassations in body.
     */
    @GetMapping("/plan-passations")
    public List<PlanPassation> getAllPlanPassations() {
        log.debug("REST request to get all PlanPassations");
        return planPassationRepository.findAll();
    }

    /**
     * {@code GET  /plan-passations/:id} : get the "id" planPassation.
     *
     * @param id the id of the planPassation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planPassation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plan-passations/{id}")
    public ResponseEntity<PlanPassation> getPlanPassation(@PathVariable Long id) {
        log.debug("REST request to get PlanPassation : {}", id);
        Optional<PlanPassation> planPassation = planPassationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planPassation);
    }

    /**
     * {@code DELETE  /plan-passations/:id} : delete the "id" planPassation.
     *
     * @param id the id of the planPassation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plan-passations/{id}")
    public ResponseEntity<Void> deletePlanPassation(@PathVariable Long id) {
        log.debug("REST request to delete PlanPassation : {}", id);
        planPassationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
