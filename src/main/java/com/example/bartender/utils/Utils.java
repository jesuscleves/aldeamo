package com.example.bartender.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utils esta clase contiene dos métodos que transforman la información de array a string y viceversa.
 */
public class Utils {

    /**
     * Método encargado de transformar un string separado por comas en un arreglo de enteros.
     *
     * @param strList lista de números enteros separados por coma en string.
     * @return List<Integer> lista de enteros.
     */
    public static List<Integer> getArrayFromString(String strList){
        return strList.equals("") ? new ArrayList<>(): Arrays
                .stream(strList.split(","))
                .map(Integer::new).collect(Collectors.toList());
    }

    /**
     * Método encargado de transformar una de enteros a un string de enteros separados por coma.
     *
     * @param integerList lista de enteros.
     * @return String lista de enteros separados por coma.
     */
    public static String getStringFromArray(List<Integer> integerList){
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
