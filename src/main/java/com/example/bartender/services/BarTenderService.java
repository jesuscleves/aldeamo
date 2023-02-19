package com.example.bartender.services;

import com.example.bartender.dtos.LogDTO;
import com.example.bartender.dtos.ResponseUserDTO;
import com.example.bartender.entities.ArrayEntity;
import com.example.bartender.repositories.ArrayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.bartender.utils.Utils.getArrayFromString;
import static com.example.bartender.utils.Utils.getStringFromArray;

/**
 * BarTenderService esta clase es el servicio encargado de realizar el proceso de iteración
 */
@Service
public class BarTenderService {

    private static Logger logger = LoggerFactory.getLogger(BarTenderService.class);//Para logs en consola

    @Autowired//Interface repositorio que me permite acceder y/o manipular los datos de la tabla arrays
    private ArrayRepository arrayRepository;

    @Value("${prime-numbers}")
    private String primeNumbers;//Lista de los números primos en string

    /**
     * Método encargado de realizar la iteraciones según el número proporcionado por el cliente
     *
     * @param qSize número de iteraciones
     * @param dataRange rango de la pila de números
     * @throws Exception genera error de tipo de datos que el controller ya está capturando
     */
    public ResponseUserDTO iterate(int qSize, int dataRange){
        List<LogDTO> logDTO = new ArrayList<>();//Variable para almacenar el proceso
        ArrayEntity arrayEntity = arrayRepository.getById(dataRange);
        String arrayA = arrayEntity.getInputArray();//Arreglo que contiene la pila de números
        List<Integer> arrayB = getArrayFromString(primeNumbers);//Arreglo de números primos
        List<Integer> arrayResult = new ArrayList<>();//Arreglo que almacena el resultado

        for (int i = 0; i < qSize; i++){//Iteramos el número de veces especificado por el usuario
            //Arreglos que almacenan temporalmente los números según el resultado de su divisibilidad
            List<Integer> newValueA = new ArrayList<>();
            List<Integer> newValueB = new ArrayList<>();
            for (int a: getArrayFromString(arrayA)){
                if(a % arrayB.get(i) == 0){//Si el residuo es cero significa que son dividibles
                    arrayResult.add(a);
                    newValueB.add(a);
                } else {
                    newValueA.add(a);
                }
            }
            arrayA = getStringFromArray(newValueA);//Asignamos el nuevo valor de los números que no son divisibles
            logDTO.add(new LogDTO(arrayA, getStringFromArray(newValueB), arrayB.get(i), String.valueOf(i + 1)));//Agregamos la iteración al log
            i = newValueA.size() == 0 ? qSize: i;//validamos que el ciclo sea productivo
        }
        //Creamos la respuesta para el usuario
        ResponseUserDTO responseUser = new ResponseUserDTO();
        responseUser.setListLogDTO(logDTO);
        responseUser.setResult(String.format("Resultado: B[%s], A[%s]",
                arrayResult.stream().map(String::valueOf).collect(Collectors.joining(",")),
                arrayA));
        return responseUser;
    }
}
