package ru.job4j.hibernate.date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


/**
 * Class Product.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 12.01.2021
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String producer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public static Product of(String name, String producer) {
        Product p = new Product();
        p.name = name;
        p.producer = producer;
        p.created = new Date(System.currentTimeMillis());
        return p;
    }
}