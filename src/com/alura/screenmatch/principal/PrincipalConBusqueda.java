package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
       //Esta variable se configuro en variables de sistema como OMDB_APIKEY
        String apiKey = System.getenv("OMDB_APIKEY");


        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el nombre de la película: ");
        var busqueda = lectura.nextLine();

        String direccion = "http://www.omdbapi.com/?t=" + busqueda + "&apikey="+apiKey;

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

        //Instanciamos el módulo Gson que descargamos de maven
        //Utilizamos el GsonBuilder() para no tener
        // que cambiar la inial de la variable a mayuscula
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        //Ahora llevamos el String tipo json que creamos de response.body

        TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);//La respuesta nos debe devolver un Titulo
        System.out.println(miTituloOmdb);
        try {
            Titulo miTitulo = new Titulo(miTituloOmdb);//La respuesta nos debe devolver un Titulo
            System.out.println(miTitulo);
        }catch (NumberFormatException e){
            System.out.println("Ocurrio un error: ");
            System.out.println(e.getMessage());
        }
        System.out.println("Finalizo la ejecución del programa");

    }
}
