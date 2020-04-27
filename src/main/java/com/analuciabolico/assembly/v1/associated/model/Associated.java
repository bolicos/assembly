package com.analuciabolico.assembly.v1.associated.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "SEQ_ASSOCIATED", sequenceName = "SEQUENCE_ASSOCIATED", allocationSize = 1)
public class Associated implements Serializable {

    public Associated(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "UID_ASSOCIATED")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ASSOCIATED")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "CPF", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "CREATED_AT", nullable = false)
    protected LocalDateTime createdAt;
}
