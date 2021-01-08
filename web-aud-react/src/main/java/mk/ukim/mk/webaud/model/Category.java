package mk.ukim.mk.webaud.model;

import lombok.Data;


import javax.persistence.*;


@Data //generira geteri i seteri
@Entity
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 4000)
    private String description;

    public Category(){}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}