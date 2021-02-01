package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.domain.SigRealisation;
import sn.ssi.sigmap.service.SigRealisationService;
import sn.ssi.sigmap.service.dto.SigRealisationDTO;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

/**
 * REST controller for managing {@link sn.ssi.sigmap.domain.SigRealisation}.
 */
@RestController
@RequestMapping("/api")
public class SigRealisationResource {

    private final Logger log = LoggerFactory.getLogger(SigRealisationResource.class);

    private static final String ENTITY_NAME = "planpassationmsSigRealisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SigRealisationService sigRealisationService;

    public SigRealisationResource(SigRealisationService sigRealisationService) {
        this.sigRealisationService = sigRealisationService;
    }

    /**
     * {@code POST  /sig-realisations} : Create a new sigRealisation.
     *
     * @param sigRealisationDTO the sigRealisation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sigRealisation, or with status {@code 400 (Bad Request)} if the sigRealisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PostMapping("/sig-realisations")
//    public ResponseEntity<SigRealisation> createSigRealisation(@Valid @RequestBody SigRealisation sigRealisation) throws URISyntaxException {
//        log.debug("REST request to save SigRealisation : {}", sigRealisation);
//        if (sigRealisation.getId() != null) {
//            throw new BadRequestAlertException("A new sigRealisation cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        SigRealisation result = sigRealisationService.save(sigRealisation);
//        return ResponseEntity.created(new URI("/api/sig-realisations/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
    @PostMapping("/sig-realisations/save")
    public ResponseEntity<SigRealisationDTO> createSigRealisation(@Valid @RequestBody SigRealisationDTO sigRealisationDTO) throws URISyntaxException, ParseException {
        log.debug("REST request to save SigRealisation : {}", sigRealisationDTO);
        if (sigRealisationDTO.getId() != null) {
            throw new BadRequestAlertException("A new sigRealisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SigRealisationDTO result = sigRealisationService.save1(sigRealisationDTO);
        return ResponseEntity.created(new URI("/api/sig-realisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

//    @PostMapping("/sig-realisations/save")
//    public ResponseEntity<?> setFields(@Valid @RequestBody SigRealisationDTO sigRealisationDTO) {
//        try {
//            SigRealisationDTO sigRealisationDTO1 = sigRealisationService.save1(sigRealisationDTO);
//            return ok().body(sigRealisationDTO1);
//        } catch (ParseException error) {
//            log.warn("format tanggal salah", error);
//            return badRequest().body("format tanggal salah");
//        } catch (NumberFormatException error) {
//            log.warn("format number salah", error);
//            return badRequest().body("format number salah");
//        }
//    }
    /**
     * {@code PUT  /sig-realisations} : Updates an existing sigRealisation.
     *
     * @param sigRealisation the sigRealisation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sigRealisation,
     * or with status {@code 400 (Bad Request)} if the sigRealisation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sigRealisation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sig-realisations")
    public ResponseEntity<SigRealisation> updateSigRealisation(@Valid @RequestBody SigRealisation sigRealisation) throws URISyntaxException {
        log.debug("REST request to update SigRealisation : {}", sigRealisation);
        if (sigRealisation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SigRealisation result = sigRealisationService.save(sigRealisation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sigRealisation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sig-realisations} : get all the sigRealisations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sigRealisations in body.
     */
    @GetMapping("/sig-realisations")
    public ResponseEntity<List<SigRealisation>> getAllSigRealisations(Pageable pageable) {
        log.debug("REST request to get a page of SigRealisations");
        Page<SigRealisation> page = sigRealisationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /* Recuperations des champs Additionnelles */
    @GetMapping("/sig-realisations/newfieds")
    public SigRealisationDTO registerNew() {
        return sigRealisationService.getFields();
    }

    /**
     * {@code GET  /sig-realisations/:id} : get the "id" sigRealisation.
     *
     * @param id the id of the sigRealisation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sigRealisation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sig-realisations/{id}")
    public ResponseEntity<SigRealisation> getSigRealisation(@PathVariable Long id) {
        log.debug("REST request to get SigRealisation : {}", id);
        Optional<SigRealisation> sigRealisation = sigRealisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sigRealisation);
    }

    /**
     * {@code DELETE  /sig-realisations/:id} : delete the "id" sigRealisation.
     *
     * @param id the id of the sigRealisation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sig-realisations/{id}")
    public ResponseEntity<Void> deleteSigRealisation(@PathVariable Long id) {
        log.debug("REST request to delete SigRealisation : {}", id);
        sigRealisationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
