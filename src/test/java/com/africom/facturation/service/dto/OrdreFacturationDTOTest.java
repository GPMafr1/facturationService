package com.africom.facturation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.africom.facturation.web.rest.TestUtil;

public class OrdreFacturationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdreFacturationDTO.class);
        OrdreFacturationDTO ordreFacturationDTO1 = new OrdreFacturationDTO();
        ordreFacturationDTO1.setId(1L);
        OrdreFacturationDTO ordreFacturationDTO2 = new OrdreFacturationDTO();
        assertThat(ordreFacturationDTO1).isNotEqualTo(ordreFacturationDTO2);
        ordreFacturationDTO2.setId(ordreFacturationDTO1.getId());
        assertThat(ordreFacturationDTO1).isEqualTo(ordreFacturationDTO2);
        ordreFacturationDTO2.setId(2L);
        assertThat(ordreFacturationDTO1).isNotEqualTo(ordreFacturationDTO2);
        ordreFacturationDTO1.setId(null);
        assertThat(ordreFacturationDTO1).isNotEqualTo(ordreFacturationDTO2);
    }
}
