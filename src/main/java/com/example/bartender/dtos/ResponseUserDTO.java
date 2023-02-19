package com.example.bartender.dtos;

import lombok.Data;//Realiza los métodos de acceso (getter y setter) el momento de compilación

import java.util.List;

/**
 * ResponseUserDTO esta clase es la estructura de datos de que me permite guardar la información que se va
 * a retornar al cliente
 */
@Data
public class ResponseUserDTO {
    private List<LogDTO> listLogDTO;
    private String result;
}
