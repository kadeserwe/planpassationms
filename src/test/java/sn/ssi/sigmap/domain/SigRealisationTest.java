package sn.ssi.sigmap.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.ssi.sigmap.web.rest.TestUtil;

public class SigRealisationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SigRealisation.class);
        SigRealisation sigRealisation1 = new SigRealisation();
        sigRealisation1.setId(1L);
        SigRealisation sigRealisation2 = new SigRealisation();
        sigRealisation2.setId(sigRealisation1.getId());
        assertThat(sigRealisation1).isEqualTo(sigRealisation2);
        sigRealisation2.setId(2L);
        assertThat(sigRealisation1).isNotEqualTo(sigRealisation2);
        sigRealisation1.setId(null);
        assertThat(sigRealisation1).isNotEqualTo(sigRealisation2);
    }
}
