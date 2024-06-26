package com.desafioliteratura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConvierteDatos implements IConvierteDatos {

    //linea adicionada
    private ObjectMapper objectMapper;
   //SE ADICIONO ESTE METODO
    public ConvierteDatos(){
        this.objectMapper=new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

   // private ObjectMapper objectMapper = new ObjectMapper();


@Override
// se cambio calss por clazz y el JsonProcessingException por el ioexception del catch
public <T> T obtenerDatos ( String json, Class<T> clazz){
    try {
        return  objectMapper.readValue(json, clazz);}
    catch (IOException e){
        throw  new RuntimeException(e);




}}}
