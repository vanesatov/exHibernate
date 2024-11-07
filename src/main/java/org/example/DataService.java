package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DataService {
    private SessionFactory sessionFactory;

    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Pelicula pelicula) {
        sessionFactory.inTransaction(session -> session.persist(pelicula));
    }


    public Pelicula findPeliculaById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Pelicula.class, id);
        }
    }

    public List<Opinion> getOpinionesByUser(String user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select o from Opinion o join o.pelicula p where o.usuario = :user", Opinion.class)
                    .setParameter("user", user)
                    .list();
        }
    }

    public void addOpinionToPelicula(Opinion opinion, Pelicula pelicula) {
        sessionFactory.inTransaction(session -> {
            pelicula.addOpinion(opinion);
            session.persist(opinion);
            session.merge(pelicula);
        });
    }

    public List<Pelicula> peliculasBajaPuntuacion(int puntuacion) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Pelicula p join p.opiniones o where o.puntuacion <= :puntuacion", Pelicula.class)
                    .setParameter("puntuacion", puntuacion)
                    .list();
        }
    }

}


