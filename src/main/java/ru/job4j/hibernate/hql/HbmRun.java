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
        Candidate rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*Candidate one = Candidate.of("Alex", "Hibernate", 50000);
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
                    .executeUpdate();*/

            /*Vacancy one = Vacancy.of("Junior", "Servlets", 50000);
            Vacancy two = Vacancy.of("Middle", "Hibernate", 60000);
            Vacancy three = Vacancy.of("Senior", "Spring", 45000);
            session.save(one);
            session.save(two);
            session.save(three);
            JobBase jobBase = JobBase.of("Enterprise");
            jobBase.addVacancy(one);
            jobBase.addVacancy(two);
            jobBase.addVacancy(three);
            session.save(jobBase);*/

            rsl = session.createQuery(
                    "select distinct c from Candidate c "
                            + "join fetch c.jobBase jb "
                            + "join fetch jb.vacancies v "
                            + "where c.id = :sId", Candidate.class
            ).setParameter("sId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}