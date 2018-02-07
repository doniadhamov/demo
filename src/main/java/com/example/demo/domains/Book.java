package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {

    @Transient
    public static final String sequenceName = "books_id_seq";

    @Id
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @JsonView(DataTablesOutput.View.class)
    private Long id;

    @NotNull
    @NotEmpty
    @JsonView(DataTablesOutput.View.class)
    private String name;

    @JsonView(DataTablesOutput.View.class)
    private Boolean inLibrary;

    @OneToOne
    @JsonView(DataTablesOutput.View.class)
    private Category category;

    @PrePersist
    public void onCreate() {
        setInLibrary(Boolean.TRUE);
    }

    public void merge(Book other) {
        this.id = other.id != null ? other.id : this.id;
        this.name = other.name != null ? other.name : this.name;
        this.inLibrary = other.inLibrary != null ? other.inLibrary : this.inLibrary;
        this.category = other.category != null ? other.category : this.category;
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

    public Boolean getInLibrary() {
        return inLibrary;
    }

    public void setInLibrary(Boolean inLibrary) {
        this.inLibrary = inLibrary;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
