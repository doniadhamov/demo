package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

    @Transient
    public static final String sequenceName = "categories_id_seq";

    @Id
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @JsonView(DataTablesOutput.View.class)
    private Long id;

    @NotNull
    @NotEmpty
    @JsonView(DataTablesOutput.View.class)
    private String name;

    public void merge(Category other) {
        this.id = other.id != null ? other.id : this.id;
        this.name = other.name != null ? other.name : this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
