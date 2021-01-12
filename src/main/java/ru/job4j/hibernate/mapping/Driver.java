package ru.job4j.hibernate.mapping;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Driver.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 12.01.2021
 */
@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "history_owner",
            joinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }
}
