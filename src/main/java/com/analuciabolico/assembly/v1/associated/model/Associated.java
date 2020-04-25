package com.analuciabolico.assembly.v1.associated.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "ASSOCIATED")
@Getter
@Entity
@NoArgsConstructor
@SequenceGenerator(name = "SEQ_ASSOCIATED", sequenceName = "SEQUENCE_ASSOCIATED", allocationSize = 1)
public class Associated {

    @Id
    @Column(name = "PK_ASSOCIATED")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ASSOCIATED")
    private Long id;
}
