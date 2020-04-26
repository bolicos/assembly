package com.analuciabolico.assembly.v1.associated.repository;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, Long> {
    Associated findByCpf(String cpf);
}
