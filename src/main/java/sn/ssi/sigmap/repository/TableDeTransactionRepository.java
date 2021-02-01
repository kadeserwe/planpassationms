package sn.ssi.sigmap.repository;

import sn.ssi.sigmap.domain.TableDeTransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TableDeTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableDeTransactionRepository extends JpaRepository<TableDeTransaction, Long> {

    List<TableDeTransaction> findByTableNameAndMasterId(String nomTable, Long masterId);
}
