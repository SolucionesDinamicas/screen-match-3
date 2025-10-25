package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto",2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Avatar",2023);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("EL señor de los anillos",180);
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
            if (item instanceof Pelicula pelicula){
            //Casteo de la clase película para que pueda recibir la clase serie como película//Pelicula pelicula = (Pelicula)item;
                System.out.println(pelicula.getClasificacion());
            }



        }
    }
}
