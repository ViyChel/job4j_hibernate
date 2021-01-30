package ru.job4j.hibernate.integration;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Order.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 30.01.2021
 */
public class Order {
    private int id;

    private String name;

    private String description;

    private Timestamp created;

    public Order() {
    }

    public Order(int id, String name, String description, Timestamp created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public static Order of(String name, String description) {
        Order o = new Order();
        o.name = name;
        o.description = description;
        o.created = new Timestamp(System.currentTimeMillis());
        return o;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}