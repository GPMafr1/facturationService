package com.africom.facturation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.facturation.web.rest.TestUtil;

public class OrdreFacturationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdreFacturation.class);
        OrdreFacturation ordreFacturation1 = new OrdreFacturation();
        ordreFacturation1.setId(1L);
        OrdreFacturation ordreFacturation2 = new OrdreFacturation();
        ordreFacturation2.setId(ordreFacturation1.getId());
        assertThat(ordreFacturation1).isEqualTo(ordreFacturation2);
        ordreFacturation2.setId(2L);
        assertThat(ordreFacturation1).isNotEqualTo(ordreFacturation2);
        ordreFacturation1.setId(null);
        assertThat(ordreFacturation1).isNotEqualTo(ordreFacturation2);
    }
}
