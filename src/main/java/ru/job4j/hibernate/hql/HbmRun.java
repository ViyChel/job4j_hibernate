package ru.job4j.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


/**
 * Class HbmRun.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 13.01.2021
 */
public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", "Hibernate", 50000);
            Candidate two = Candidate.of("Nikolay", "Spring", 60000);
            Candidate three = Candidate.of("Nikita", "Servlets", 45000);
            session.save(one);
            session.save(two);
            session.save(three);

            Query query1 = session.createQuery("from Candidate");
            for (Object st : query1.list()) {
                System.out.println(st);
            }

            Query query2 = session.createQuery("from Candidate s where s.id = :fId");
            query2.setParameter("fId", 2);
            System.out.println(query2.uniqueResult());

            Query query3 = session.createQuery("from Candidate s where s.name = :fName");
            query3.setParameter("fName", "Nikita");
            System.out.println(query3.uniqueResult());

            session.createQuery("insert into Candidate (name, experience, salary) "
                    + "select concat(c.name, 'NEW'), c.experience, c.salary + 10000  "
                    + "from Candidate c where c.id = :fId")
                    .setParameter("fId", 2)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}