package com.pompDirect;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class Home {


    @Test
    public void crud() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        create(session);
        read(session);

        update(session);
        read(session);

        delete(session);
        read(session);

        session.close();
    }

    private void delete(Session session) {
        System.out.println("Deleting mondeo record...");
        Student mondeo = (Student) session.get(Student.class, "mondeo");

        session.beginTransaction();
        session.delete(mondeo);
        session.getTransaction().commit();
    }

    private void update(Session session) {
        System.out.println("Updating mustang price...");
        Student mustang = (Student) session.get(Student.class, "mustang");
        mustang.setName("mustang");
        mustang.setPrice(35250);

        session.beginTransaction();
        session.saveOrUpdate(mustang);
        session.getTransaction().commit();
    }

    private void create(Session session) {
        System.out.println("Creating car records...");
        Student mustang = new Student();
        mustang.setName("mustang");
        mustang.setPrice(40000);

        Student mondeo = new Student();
        mondeo.setName("mondeo");
        mondeo.setPrice(20000);

        session.beginTransaction();
        session.save(mustang);
        session.save(mondeo);
        session.getTransaction().commit();
    }

    private void read(Session session) {
        Query q = session.createQuery("select name from Student name");

        List cars = q.list();

        System.out.println("Reading car records...");
        System.out.printf("%-30.30s  %-30.30s%n", "Model", "Price");
        for (Object c : cars) {
            System.out.printf("%-30.30s  %-30.30s%n", c, c);
        }
    }

}
