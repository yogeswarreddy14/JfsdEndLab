package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Inserting records
            Project p1 = new Project("Project A", 12, 500000, "John Doe");
            Project p2 = new Project("Project B", 8, 300000, "Jane Smith");
            Project p3 = new Project("Project C", 6, 200000, "Alice Brown");

            session.persist(p1);
            session.persist(p2);
            session.persist(p3);

            transaction.commit();

            // Aggregate operations
            @SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Project.class);

            // Count
            criteria.setProjection(Projections.rowCount());
            System.out.println("Total Projects: " + criteria.uniqueResult());

            // Max Budget
            criteria.setProjection(Projections.max("budget"));
            System.out.println("Maximum Budget: " + criteria.uniqueResult());

            // Min Budget
            criteria.setProjection(Projections.min("budget"));
            System.out.println("Minimum Budget: " + criteria.uniqueResult());

            // Sum of Budgets
            criteria.setProjection(Projections.sum("budget"));
            System.out.println("Total Budget: " + criteria.uniqueResult());

            // Average Budget
            criteria.setProjection(Projections.avg("budget"));
            System.out.println("Average Budget: " + criteria.uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
