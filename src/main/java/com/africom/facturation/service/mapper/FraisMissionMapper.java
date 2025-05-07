package com.africom.facturation.service.mapper;


import com.africom.facturation.domain.*;
import com.africom.facturation.service.dto.FraisMissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FraisMission} and its DTO {@link FraisMissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FraisMissionMapper extends EntityMapper<FraisMissionDTO, FraisMission> {



    default FraisMission fromId(Long id) {
        if (id == null) {
            return null;
        }
        FraisMission fraisMission = new FraisMission();
        fraisMission.setId(id);
        return fraisMission;
    }
}
