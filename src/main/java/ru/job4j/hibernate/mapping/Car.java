package ru.job4j.hibernate.mapping;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Car.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 12.01.2021
 */
@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;
    @ManyToMany(mappedBy = "cars")
    List<Driver> drivers = new ArrayList<>();

    public static Car of(Engine engine) {
        Car car = new Car();
        car.setEngine(engine);
        return car;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }
}
