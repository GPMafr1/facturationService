package com.africom.facturation.service.mapper;


import com.africom.facturation.domain.*;
import com.africom.facturation.service.dto.OrdreFacturationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrdreFacturation} and its DTO {@link OrdreFacturationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrdreFacturationMapper extends EntityMapper<OrdreFacturationDTO, OrdreFacturation> {



    default OrdreFacturation fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrdreFacturation ordreFacturation = new OrdreFacturation();
        ordreFacturation.setId(id);
        return ordreFacturation;
    }
}
