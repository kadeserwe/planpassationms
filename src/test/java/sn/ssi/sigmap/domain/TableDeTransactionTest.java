package sn.ssi.sigmap.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.ssi.sigmap.web.rest.TestUtil;

public class TableDeTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableDeTransaction.class);
        TableDeTransaction tableDeTransaction1 = new TableDeTransaction();
        tableDeTransaction1.setId(1L);
        TableDeTransaction tableDeTransaction2 = new TableDeTransaction();
        tableDeTransaction2.setId(tableDeTransaction1.getId());
        assertThat(tableDeTransaction1).isEqualTo(tableDeTransaction2);
        tableDeTransaction2.setId(2L);
        assertThat(tableDeTransaction1).isNotEqualTo(tableDeTransaction2);
        tableDeTransaction1.setId(null);
        assertThat(tableDeTransaction1).isNotEqualTo(tableDeTransaction2);
    }
}
