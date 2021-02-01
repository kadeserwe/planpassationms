package sn.ssi.sigmap.repository;

import sn.ssi.sigmap.domain.TableRow;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TableRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableRowRepository extends JpaRepository<TableRow, Long> {
    List<TableRow> findByTableName(String tableName);
}
