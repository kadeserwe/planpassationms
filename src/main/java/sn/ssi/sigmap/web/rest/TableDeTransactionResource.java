package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.domain.TableDeTransaction;
import sn.ssi.sigmap.repository.TableDeTransactionRepository;
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
 * REST controller for managing {@link sn.ssi.sigmap.domain.TableDeTransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TableDeTransactionResource {

    private final Logger log = LoggerFactory.getLogger(TableDeTransactionResource.class);

    private final TableDeTransactionRepository tableDeTransactionRepository;

    public TableDeTransactionResource(TableDeTransactionRepository tableDeTransactionRepository) {
        this.tableDeTransactionRepository = tableDeTransactionRepository;
    }

    /**
     * {@code GET  /table-de-transactions} : get all the tableDeTransactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableDeTransactions in body.
     */
    @GetMapping("/table-de-transactions")
    public List<TableDeTransaction> getAllTableDeTransactions() {
        log.debug("REST request to get all TableDeTransactions");
        return tableDeTransactionRepository.findAll();
    }

    /**
     * {@code GET  /table-de-transactions/:id} : get the "id" tableDeTransaction.
     *
     * @param id the id of the tableDeTransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableDeTransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-de-transactions/{id}")
    public ResponseEntity<TableDeTransaction> getTableDeTransaction(@PathVariable Long id) {
        log.debug("REST request to get TableDeTransaction : {}", id);
        Optional<TableDeTransaction> tableDeTransaction = tableDeTransactionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tableDeTransaction);
    }
}
