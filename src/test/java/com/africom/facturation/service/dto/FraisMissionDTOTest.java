package com.africom.facturation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.facturation.web.rest.TestUtil;

public class FraisMissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FraisMissionDTO.class);
        FraisMissionDTO fraisMissionDTO1 = new FraisMissionDTO();
        fraisMissionDTO1.setId(1L);
        FraisMissionDTO fraisMissionDTO2 = new FraisMissionDTO();
        assertThat(fraisMissionDTO1).isNotEqualTo(fraisMissionDTO2);
        fraisMissionDTO2.setId(fraisMissionDTO1.getId());
        assertThat(fraisMissionDTO1).isEqualTo(fraisMissionDTO2);
        fraisMissionDTO2.setId(2L);
        assertThat(fraisMissionDTO1).isNotEqualTo(fraisMissionDTO2);
        fraisMissionDTO1.setId(null);
        assertThat(fraisMissionDTO1).isNotEqualTo(fraisMissionDTO2);
    }
}
