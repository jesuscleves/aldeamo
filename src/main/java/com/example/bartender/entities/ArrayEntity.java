package com.example.bartender.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Clase entidad que representa la tabla arrays
 *
 */
@Entity
@Table(name = "arrays")
@Data
public class ArrayEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id//Columna id de la tabla sin generador automático
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "input_array", nullable = false)//Columna que contiene la pila de números
    private String inputArray;
}
