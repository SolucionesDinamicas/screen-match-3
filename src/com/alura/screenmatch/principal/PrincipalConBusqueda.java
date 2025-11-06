package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Esta variable se configuro en variables de sistema como OMDB_APIKEY
        String apiKey = System.getenv("OMDB_APIKEY");


        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        //Instanciamos el módulo Gson que descargamos de maven
        //Utilizamos el GsonBuilder() para no tener
        // que cambiar la incial de la variable a mayuscula
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()//Mejora la visualización de la salida del JSON
                .create();

        while (true){
            System.out.println("Escriba el nombre de la película: ");
            var busqueda = lectura.nextLine();

            if (busqueda.equals("salir")){
                break;
            }

            // Codificamos la búsqueda correctamente para no generar errores con espacios o caracteres especiales
            String busquedaCodificada = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);

            String direccion = "http://www.omdbapi.com/?t=" + busquedaCodificada + "&apikey="+apiKey;

            try {
                //Nosotros somos el cliente que hace la solicitud
                HttpClient client = HttpClient.newHttpClient();
                //Requirimiento al servidor
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build(); //Patron de diseño llamado builder forma de construir algo

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //Levamos la salida json a un String
                String json = response.body();
                System.out.println(response.body());


                //Ahora llevamos el String tipo json que creamos de response.body

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);//La respuesta nos debe devolver un Titulo
                System.out.println(miTituloOmdb);

                Titulo miTitulo = new Titulo(miTituloOmdb);//La respuesta nos debe devolver un Titulo
                System.out.println("Título ya convertido: " + miTitulo);
                //Va agregando los miTitulo al arreglo titulos
                titulos.add(miTitulo);
            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e) {
                System.out.println("Error en la URI, verifique la dirección.");
//            System.out.println(e.getMessage());
            }catch (ErrorEnConversionDeDuracionException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);
        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));//Convertimos el título a un archivo .JSON
        escritura.close();

        System.out.println("Finalizo la ejecución del programa");

    }


}

//Escribir el valor del toString de la película en un txt
//FileWriter escritura = new FileWriter("peliculas.txt");
//        escritura.write(miTitulo.toString());//Abrir escritura
//        escritura.close();//Importante cerrar la escritura
