package com.desafioliteratura.literatura.service;

public class ConsumoAPITest {

        public static void main(String[] args) {
            ConsumoAPI consumoAPI = new ConsumoAPI();
            String url = "https://gutendex.com/books/?search=game%20of%20thrones";
            String json = consumoAPI.obtenerDatos(url);
            System.out.println(json);
        }

}
