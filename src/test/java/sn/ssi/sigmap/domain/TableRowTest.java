package sn.ssi.sigmap.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.ssi.sigmap.web.rest.TestUtil;

public class TableRowTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableRow.class);
        TableRow tableRow1 = new TableRow();
        tableRow1.setId(1L);
        TableRow tableRow2 = new TableRow();
        tableRow2.setId(tableRow1.getId());
        assertThat(tableRow1).isEqualTo(tableRow2);
        tableRow2.setId(2L);
        assertThat(tableRow1).isNotEqualTo(tableRow2);
        tableRow1.setId(null);
        assertThat(tableRow1).isNotEqualTo(tableRow2);
    }
}
