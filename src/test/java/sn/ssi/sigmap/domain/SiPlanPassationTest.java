package sn.ssi.sigmap.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.ssi.sigmap.web.rest.TestUtil;

public class SiPlanPassationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiPlanPassation.class);
        SiPlanPassation siPlanPassation1 = new SiPlanPassation();
        siPlanPassation1.setId(1L);
        SiPlanPassation siPlanPassation2 = new SiPlanPassation();
        siPlanPassation2.setId(siPlanPassation1.getId());
        assertThat(siPlanPassation1).isEqualTo(siPlanPassation2);
        siPlanPassation2.setId(2L);
        assertThat(siPlanPassation1).isNotEqualTo(siPlanPassation2);
        siPlanPassation1.setId(null);
        assertThat(siPlanPassation1).isNotEqualTo(siPlanPassation2);
    }
}
