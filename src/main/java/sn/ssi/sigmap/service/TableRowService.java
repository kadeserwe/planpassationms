package sn.ssi.sigmap.service;

import sn.ssi.sigmap.domain.TableRow;
import sn.ssi.sigmap.repository.TableRowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TableRow}.
 */
@Service
@Transactional
public class TableRowService {

    private final Logger log = LoggerFactory.getLogger(TableRowService.class);

    private final TableRowRepository tableRowRepository;

    public TableRowService(TableRowRepository tableRowRepository) {
        this.tableRowRepository = tableRowRepository;
    }

    /**
     * Save a tableRow.
     *
     * @param tableRow the entity to save.
     * @return the persisted entity.
     */
    public TableRow save(TableRow tableRow) {
        log.debug("Request to save TableRow : {}", tableRow);
        return tableRowRepository.save(tableRow);
    }

    /**
     * Get all the tableRows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TableRow> findAll(Pageable pageable) {
        log.debug("Request to get all TableRows");
        return tableRowRepository.findAll(pageable);
    }


    /**
     * Get one tableRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TableRow> findOne(Long id) {
        log.debug("Request to get TableRow : {}", id);
        return tableRowRepository.findById(id);
    }

    /**
     * Delete the tableRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TableRow : {}", id);
        tableRowRepository.deleteById(id);
    }
}
