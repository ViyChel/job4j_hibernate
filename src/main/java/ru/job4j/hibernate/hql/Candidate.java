package ru.job4j.hibernate.hql;

import lombok.Data;

import javax.persistence.*;

/**
 * Class Candidate.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
@Data
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String experience;
    private int salary;

    @OneToOne(fetch = FetchType.LAZY)
    private JobBase jobBase;

    public static Candidate of(String name, String experience, int salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
    }
}
