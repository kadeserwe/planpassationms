package sn.ssi.sigmap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sn.ssi.sigmap.domain.ConfGenSequence;
import sn.ssi.sigmap.domain.TableDeTransaction;
import sn.ssi.sigmap.domain.TableRow;
import sn.ssi.sigmap.repository.ConfGenSequenceRepository;
import sn.ssi.sigmap.repository.TableDeTransactionRepository;
import sn.ssi.sigmap.repository.TableRowRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TableDao {

    @Autowired
    private TableRowRepository rowRepository;
    @Autowired
    private ConfGenSequenceRepository confGenSequenceRepository;
    @Autowired
    private TableDeTransactionRepository tableDeTransactionRepository;

    public List<TableRow> findColumnsByTableName(String tableName) {
        return rowRepository.findByTableName(tableName);
    }

    public List<TableDeTransaction> findColumnByTableNameAndMasterId(String nomTable, Long masterId){
        return tableDeTransactionRepository.findByTableNameAndMasterId(nomTable, masterId);
    }

    public List<TableDeTransaction> save(String tableName, Long primaryId, List<TableDeTransaction> rows) {
        List<TableDeTransaction> datas = new ArrayList<>();
        confGenSequenceRepository.incrementByTableName(tableName);
        ConfGenSequence generator = confGenSequenceRepository.findByTableName(tableName);

        for (TableDeTransaction row : rows) {
            row.setMasterId(primaryId);
            row.setTableName(generator.getTableName());
            row.setNumeroLigne(generator.getCurrentValue());
            datas.add(tableDeTransactionRepository.save(row));
        }

        return datas;
    }

    public List<TableDeTransaction> update(String tableName, Long primaryId,List<TableDeTransaction> rows) {
        List<TableDeTransaction> datas = new ArrayList<>();
        ConfGenSequence generator = confGenSequenceRepository.findByTableName(tableName);

        for (TableDeTransaction row : rows) {
            row.setTableName(generator.getTableName());
            row.setNumeroLigne(generator.getCurrentValue());
            row.setMasterId(primaryId);
            datas.add(tableDeTransactionRepository.save(row));
        }

        return datas;
    }
}
