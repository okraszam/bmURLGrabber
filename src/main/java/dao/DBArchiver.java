package dao;

import com.mysql.cj.jdbc.Blob;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBArchiver {

    public static void addURLDescriptionToDB (String dateOfArchivisation, String shortFormOfURL, String completeURL) {

        URLDescription urlDescription = new URLDescription(dateOfArchivisation, shortFormOfURL, completeURL);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bmURLGrabberDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(urlDescription);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static void addURLContentToDB (Blob urlContent) {

        URLContent urlContentToDB = new URLContent(urlContent);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bmURLGrabberDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(urlContent);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
