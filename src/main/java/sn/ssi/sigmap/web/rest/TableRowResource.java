package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.domain.TableRow;
import sn.ssi.sigmap.service.TableRowService;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sn.ssi.sigmap.domain.TableRow}.
 */
@RestController
@RequestMapping("/api")
public class TableRowResource {

    private final Logger log = LoggerFactory.getLogger(TableRowResource.class);

    private static final String ENTITY_NAME = "planpassationmsTableRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableRowService tableRowService;

    public TableRowResource(TableRowService tableRowService) {
        this.tableRowService = tableRowService;
    }

    /**
     * {@code POST  /table-rows} : Create a new tableRow.
     *
     * @param tableRow the tableRow to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableRow, or with status {@code 400 (Bad Request)} if the tableRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-rows")
    public ResponseEntity<TableRow> createTableRow(@Valid @RequestBody TableRow tableRow) throws URISyntaxException {
        log.debug("REST request to save TableRow : {}", tableRow);
        if (tableRow.getId() != null) {
            throw new BadRequestAlertException("A new tableRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableRow result = tableRowService.save(tableRow);
        return ResponseEntity.created(new URI("/api/table-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-rows} : Updates an existing tableRow.
     *
     * @param tableRow the tableRow to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableRow,
     * or with status {@code 400 (Bad Request)} if the tableRow is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableRow couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-rows")
    public ResponseEntity<TableRow> updateTableRow(@Valid @RequestBody TableRow tableRow) throws URISyntaxException {
        log.debug("REST request to update TableRow : {}", tableRow);
        if (tableRow.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TableRow result = tableRowService.save(tableRow);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableRow.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /table-rows} : get all the tableRows.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableRows in body.
     */
    @GetMapping("/table-rows")
    public ResponseEntity<List<TableRow>> getAllTableRows(Pageable pageable) {
        log.debug("REST request to get a page of TableRows");
        Page<TableRow> page = tableRowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /table-rows/:id} : get the "id" tableRow.
     *
     * @param id the id of the tableRow to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableRow, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-rows/{id}")
    public ResponseEntity<TableRow> getTableRow(@PathVariable Long id) {
        log.debug("REST request to get TableRow : {}", id);
        Optional<TableRow> tableRow = tableRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableRow);
    }

    /**
     * {@code DELETE  /table-rows/:id} : delete the "id" tableRow.
     *
     * @param id the id of the tableRow to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-rows/{id}")
    public ResponseEntity<Void> deleteTableRow(@PathVariable Long id) {
        log.debug("REST request to delete TableRow : {}", id);
        tableRowService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
