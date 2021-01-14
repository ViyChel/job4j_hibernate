package ru.job4j.hibernate.hql;

import lombok.Data;

import javax.persistence.*;

/**
 * Class Vacancy.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
@Data
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String experience;
    private int salary;

    public static Vacancy of(String description, String experience, int salary) {
        Vacancy vacancy = new Vacancy();
        vacancy.description = description;
        vacancy.experience = experience;
        vacancy.salary = salary;
        return vacancy;
    }
}
