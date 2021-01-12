package ru.job4j.hibernate.mapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class Main.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 12.01.2021
 */
public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            final Engine engine1 = new Engine();
            engine1.setName("Gasoline engine");
            final Engine engine2 = new Engine();
            engine2.setName("Gas engine");
            final Engine engine3 = new Engine();
            engine3.setName("Diesel engine");
            session.save(engine1);
            session.save(engine2);
            session.save(engine3);

            final Car car1 = Car.of(engine1);
            final Car car2 = Car.of(engine2);
            final Car car3 = Car.of(engine3);

            final Driver driver1 = new Driver();
            final Driver driver2 = new Driver();
            final Driver driver3 = new Driver();
            driver1.addCar(car3);
            driver2.addCar(car2);
            driver3.addCar(car2);
            session.save(driver1);
            session.save(driver2);
            session.save(driver3);
            car2.addDriver(driver2);
            car2.addDriver(driver3);
            car3.addDriver(driver1);
            session.save(car1);
            session.save(car2);
            session.save(car3);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
