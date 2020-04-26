package com.analuciabolico.assembly.v1.associated.service.interfaces;

import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IAssociatedService {

    ResourceCreated save(AssociatedDto schedule);

    Associated findById(Long id);

    List<Associated> findAll(Sort sort);

}
