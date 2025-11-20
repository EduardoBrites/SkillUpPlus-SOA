package com.example.SkillUpPlus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "objectives")
public class ObjectiveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome do objetivo é obrigatório")
    private String name;

    private String description;

    @FutureOrPresent(message = "A data deve ser hoje ou futura.")
    private LocalDate targetDate;

    @DecimalMin(value = "0.0", message = "O valor da meta não pode ser negativo.")
    private BigDecimal targetValue;

    private LocalDate creationDate;

    @PrePersist
    public void prePersist() { if ( creationDate == null ) { creationDate = LocalDate.now(); }}

}
