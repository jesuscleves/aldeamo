package com.example.bartender.dtos;

import lombok.Data;//Realiza los métodos de acceso (getter y setter) el momento de compilación

/**
 * LogDTO esta clase es la estructura de datos de que me permite organizar la información
 * de cada una de las iteraciones.
*/
@Data
public class LogDTO {
    private String arrayA;//Los números naturales mayores a 1 que no fueron divisibles en la iteración
    private String arrayB;//Los número naturales mayores a 1 que fueron divisibles en la iteración
    private Integer primeNumber;//Número primo
    private String qIterator;//Número fase de la iteración

    public LogDTO(String arrayA, String arrayB, Integer primeNumber, String qIterator) {
        this.arrayA = arrayA;
        this.arrayB = arrayB;
        this.primeNumber = primeNumber;
        this.qIterator = qIterator;
    }

    public LogDTO() {
    }
}
