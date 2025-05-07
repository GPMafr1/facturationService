package com.africom.facturation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrdreFacturationMapperTest {

    private OrdreFacturationMapper ordreFacturationMapper;

    @BeforeEach
    public void setUp() {
        ordreFacturationMapper = new OrdreFacturationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ordreFacturationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ordreFacturationMapper.fromId(null)).isNull();
    }
}
