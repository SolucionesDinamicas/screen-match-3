package com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el nombre de la película: ");
        var busqueda = lectura.nextLine();

        String direccion = "http://www.omdbapi.com/?t=" + busqueda + "&apikey=8f3dba8f";

        //Nosotros somos el cliente que hace la solicitud
        HttpClient client = HttpClient.newHttpClient();
        //Requrimiento al servidor
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build(); //Patron de diseño llamado builder forma de construir algo

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
