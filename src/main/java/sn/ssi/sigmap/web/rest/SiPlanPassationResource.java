package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.domain.Audit;
import sn.ssi.sigmap.domain.SiPlanPassation;
import sn.ssi.sigmap.service.AuditService;
import sn.ssi.sigmap.service.SiPlanPassationService;
import sn.ssi.sigmap.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sn.ssi.sigmap.domain.SiPlanPassation}.
 */
@RestController
@RequestMapping("/api")
public class SiPlanPassationResource {

    private final Logger log = LoggerFactory.getLogger(SiPlanPassationResource.class);

    private static final String ENTITY_NAME = "planpassationmsSiPlanPassation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SiPlanPassationService siPlanPassationService;

    private final AuditService auditService;

    private Date date = new Date();

    private LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();



    public SiPlanPassationResource(SiPlanPassationService siPlanPassationService, AuditService auditService) {
        this.siPlanPassationService = siPlanPassationService;
        this.auditService = auditService;
    }

    /**
     * {@code POST  /si-plan-passations} : Create a new siPlanPassation.
     *
     * @param siPlanPassation the siPlanPassation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new siPlanPassation, or with status {@code 400 (Bad Request)} if the siPlanPassation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/si-plan-passations")
    public ResponseEntity<SiPlanPassation> createSiPlanPassation(@Valid @RequestBody SiPlanPassation siPlanPassation) throws URISyntaxException {
        log.debug("REST request to save SiPlanPassation : {}", siPlanPassation);
        if (siPlanPassation.getId() != null) {
            throw new BadRequestAlertException("A new siPlanPassation cannot already have an ID", ENTITY_NAME, "idexists");
        }

        SiPlanPassation result = siPlanPassationService.save(siPlanPassation);
        Audit audit = new Audit();
        audit.setDate(localDate);
        audit.entite(ENTITY_NAME);
        auditService.save(audit);
        return ResponseEntity.created(new URI("/api/si-plan-passations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /si-plan-passations} : Updates an existing siPlanPassation.
     *
     * @param siPlanPassation the siPlanPassation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated siPlanPassation,
     * or with status {@code 400 (Bad Request)} if the siPlanPassation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the siPlanPassation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/si-plan-passations")
    public ResponseEntity<SiPlanPassation> updateSiPlanPassation(@Valid @RequestBody SiPlanPassation siPlanPassation) throws URISyntaxException {
        log.debug("REST request to update SiPlanPassation : {}", siPlanPassation);
        if (siPlanPassation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SiPlanPassation result = siPlanPassationService.save(siPlanPassation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, siPlanPassation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /si-plan-passations} : get all the siPlanPassations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of siPlanPassations in body.
     */
    @GetMapping("/si-plan-passations")
    public ResponseEntity<List<SiPlanPassation>> getAllSiPlanPassations(Pageable pageable) {
        log.debug("REST request to get a page of SiPlanPassations");
        Page<SiPlanPassation> page = siPlanPassationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /si-plan-passations/:id} : get the "id" siPlanPassation.
     *
     * @param id the id of the siPlanPassation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the siPlanPassation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/si-plan-passations/{id}")
    public ResponseEntity<SiPlanPassation> getSiPlanPassation(@PathVariable Long id) {
        log.debug("REST request to get SiPlanPassation : {}", id);
        Optional<SiPlanPassation> siPlanPassation = siPlanPassationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(siPlanPassation);
    }

    /**
     * {@code DELETE  /si-plan-passations/:id} : delete the "id" siPlanPassation.
     *
     * @param id the id of the siPlanPassation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/si-plan-passations/{id}")
    public ResponseEntity<Void> deleteSiPlanPassation(@PathVariable Long id) {
        log.debug("REST request to delete SiPlanPassation : {}", id);
        siPlanPassationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
