package com.africom.facturation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.facturation.web.rest.TestUtil;

public class FraisMissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FraisMission.class);
        FraisMission fraisMission1 = new FraisMission();
        fraisMission1.setId(1L);
        FraisMission fraisMission2 = new FraisMission();
        fraisMission2.setId(fraisMission1.getId());
        assertThat(fraisMission1).isEqualTo(fraisMission2);
        fraisMission2.setId(2L);
        assertThat(fraisMission1).isNotEqualTo(fraisMission2);
        fraisMission1.setId(null);
        assertThat(fraisMission1).isNotEqualTo(fraisMission2);
    }
}
