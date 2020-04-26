package com.analuciabolico.assembly.v1.associated.dto;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssociatedDto implements Serializable {

    private String name;
    private String cpf;

    public Associated convertToAssociated(){
        return new Associated(null, this.name, this.cpf, LocalDateTime.now());
    }
}
