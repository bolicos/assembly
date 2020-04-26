package com.analuciabolico.assembly.v1.associated.service.implementation;

import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.associated.service.interfaces.IAssociatedService;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssociatedService implements IAssociatedService {

    private final AssociatedRepository associatedRepository;

    @Override
    public ResourceCreated save(AssociatedDto associatedDto) {
        return new ResourceCreated(associatedRepository.save(associatedDto.convertToAssociated()).getId());
    }

    @Override
    public Associated findById(Long id) {
        return associatedRepository.getOne(id);
    }

    @Override
    public List<Associated> findAll(Sort sort) {
        return associatedRepository.findAll(sort);
    }
}
