package org.example;


import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        DataService serv = new DataService(HibernateUtil.getSessionFactory());


        // Pelicula p = new Pelicula();
        //p.setTitulo("La guerra de las galaxias");

        // serv.save(p);


        //List<Opinion> opiniones = serv.getOpinionesByUser("user1@example.com");
       // for (Opinion opinion : opiniones) {
        //    System.out.println(opinion);
       // }


        //Pelicula p = serv.findPeliculaById(1L);


       // Opinion opinion = new Opinion();
        //opinion.setDescripcion("Excelente película");
       // opinion.setUsuario("user1@example.com");
       // opinion.setPuntuacion(8);

       // serv.addOpinionToPelicula(opinion, p);


        serv.peliculasBajaPuntuacion(3).forEach(System.out::println);

    }
}
