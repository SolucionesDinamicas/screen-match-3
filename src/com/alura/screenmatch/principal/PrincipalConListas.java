package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto",2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Avatar",2023);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("EL señor de los anillos",2001);
        peliculaDeBruno.evalua(10);
        Serie lost = new Serie("Lost",2000);

        //Crear un array
        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeBruno);
        lista.add(lost);

//        lista.forEach(Titulo::muestraFichaTecnica);
        //Recorrer cada item de la lista y mostrar el toString

        for (Titulo item: lista) {
            System.out.println(item.getNombre());

            //Verifica si el item es de tipo(instanceof) película y se castea con la variable pelicula al final
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2){
            //Casteo de la clase película para que pueda recibir la clase serie como película//Pelicula pelicula = (Pelicula)item;
                System.out.println("Clasificación; " +  pelicula.getClasificacion());
            }

        }
        List<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penelope cruz");
        listaDeArtistas.add("Antonio banderas");
        listaDeArtistas.add("Ricardo Darin");

        System.out.println("Lista de artistas NO ordenada " + listaDeArtistas);

        //Ordenar una lista con el paquete Colletions
        Collections.sort(listaDeArtistas);

        System.out.println("Lista de artistas ordenada " + listaDeArtistas);

        //Ordenar títulos
        Collections.sort(lista);
        System.out.println("Lista de títulos ordenados: " + lista);

        //Al sort le estamos pasando la interfaz Comparator y llamamos el método comparing y sobre comparing
//        le enviamos la fecha de lazamiento para comparar
        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));

        System.out.println("Ordenado por fecha de lanzamiento: " + lista);

    }
}
