package com.africom.facturation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FraisMissionMapperTest {

    private FraisMissionMapper fraisMissionMapper;

    @BeforeEach
    public void setUp() {
        fraisMissionMapper = new FraisMissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fraisMissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fraisMissionMapper.fromId(null)).isNull();
    }
}
