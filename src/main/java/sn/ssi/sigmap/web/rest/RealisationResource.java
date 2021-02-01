package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.domain.Realisation;
import sn.ssi.sigmap.repository.RealisationRepository;
import sn.ssi.sigmap.service.RealisationService;
import sn.ssi.sigmap.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.badRequest;

/**
 * REST controller for managing {@link sn.ssi.sigmap.domain.Realisation}.
 */
@RestController
@RequestMapping("/api")
public class RealisationResource {

    private final Logger log = LoggerFactory.getLogger(RealisationResource.class);

    private static final String ENTITY_NAME = "planpassationmsRealisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RealisationService realisationService;

    private final RealisationRepository realisationRepository;

    public RealisationResource(RealisationService realisationService,RealisationRepository realisationRepository) {
        this.realisationService = realisationService;
        this.realisationRepository = realisationRepository;
    }

    /**
     * {@code POST  /realisations} : Create a new realisation.
     *
//     * @param  the realisationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new realisationDTO, or with status {@code 400 (Bad Request)} if the realisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PostMapping("/realisations")
//    public ResponseEntity<RealisationDTO> createRealisation(@Valid @RequestBody RealisationDTO realisationDTO) throws URISyntaxException {
//        log.debug("REST request to save Realisation : {}", realisationDTO);
//        if (realisationDTO.getId() != null) {
//            throw new BadRequestAlertException("A new realisation cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        RealisationDTO result = realisationService.save(realisationDTO);
//        return ResponseEntity.created(new URI("/api/realisations/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//    @PostMapping("/realisations")
//    public ResponseEntity<RealisationDTO> createRealisation(@Valid @RequestBody RealisationDTO realisationDTO) throws URISyntaxException, ParseException {
//        log.debug("REST request to save Realisation : {}", realisationDTO);
//        if (realisationDTO.getId() != null) {
//            throw new BadRequestAlertException("A new realisation cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        RealisationDTO result = realisationService.save1(realisationDTO);
//        return ResponseEntity.created(new URI("/api/realisations/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }



    @PostMapping("/realisations")
    public ResponseEntity<Realisation> createRealisation(@Valid @RequestBody Realisation realisation) throws URISyntaxException {
        log.debug("REST request to save SigRealisation : {}", realisation);
        if (realisation.getId() != null) {
            throw new BadRequestAlertException("A new sigRealisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Realisation result = realisationRepository.save(realisation);
        return ResponseEntity.created(new URI("/api/realisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /realisations} : Updates an existing realisation.
     *
     * @param realisation the realisationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated realisationDTO,
     * or with status {@code 400 (Bad Request)} if the realisation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the realisationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/realisations")
//    public ResponseEntity<RealisationDTO> updateRealisation(@Valid @RequestBody RealisationDTO realisationDTO) throws URISyntaxException {
//        log.debug("REST request to update Realisation : {}", realisationDTO);
//        if (realisationDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        RealisationDTO result = realisationService.save(realisationDTO);
//        return ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, realisationDTO.getId().toString()))
//            .body(result);
//    }
    @PutMapping("/realisations")
    public ResponseEntity<Realisation> updateRealisation(@Valid @RequestBody Realisation realisation) throws URISyntaxException {
        log.debug("REST request to update SigRealisation : {}", realisation);
        if (realisation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Realisation result = realisationRepository.save(realisation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, realisation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /realisations} : get all the realisations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of realisations in body.
     */
//    @GetMapping("/realisations")
//    public ResponseEntity<List<RealisationDTO>> getAllRealisations(Pageable pageable) {
//        log.debug("REST request to get a page of Realisations");
//        Page<RealisationDTO> page = realisationService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ok().headers(headers).body(page.getContent());
//    }
    /**
     * {@code GET  /sig-realisations} : get all the sigRealisations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sigRealisations in body.
     */
    @GetMapping("/realisations")
    public List<Realisation> getAllRealisations() {
        log.debug("REST request to get all SigRealisations");
        return realisationRepository.findAll();
    }



    /**
     * {@code GET  /realisations/:id} : get the "id" realisation.
     *
     * @param id the id of the realisationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the realisationDTO, or with status {@code 404 (Not Found)}.
     */
//    @GetMapping("/realisations/{id}")
//    public ResponseEntity<RealisationDTO> getRealisation(@PathVariable Long id) {
//        log.debug("REST request to get Realisation : {}", id);
//        Optional<RealisationDTO> realisationDTO = realisationService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(realisationDTO);
//    }

//    @GetMapping("/{id}/findById")
//    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
//        try {
//            return ok(realisationService.getFields(id));
//        } catch (NoSuchElementException emptyData) {
//            return notFound().build();
//        }
//    }


    /**
     * {@code DELETE  /realisations/:id} : delete the "id" realisation.
     *
     * @param id the id of the realisationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */

    @DeleteMapping("/realisations/{id}")
    public ResponseEntity<Void> deleteRealisation(@PathVariable Long id) {
        log.debug("REST request to delete Realisation : {}", id);
        realisationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
