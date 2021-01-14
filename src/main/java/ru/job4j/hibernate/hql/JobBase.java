package ru.job4j.hibernate.hql;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class JobBase.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
@Data
@Entity
@Table(name = "jobbase")
public class JobBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public static JobBase of(String name) {
        JobBase jobBase = new JobBase();
        jobBase.name = name;
        return jobBase;
    }

    public void addVacancy(Vacancy vacancy) {
        this.vacancies.add(vacancy);
    }
}