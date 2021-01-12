package ru.job4j.hibernate.mapping;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class Engine.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 12.01.2021
 */
@Entity
@Table(name = "engines")
@Data
@NoArgsConstructor
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
