package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;

@Entity
@Table(name = "translations")
public class Translation {
    @Transient
    private static final String sequenceName = "translations_id_seq";

    @Id
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @JsonView(DataTablesOutput.View.class)
    private Long id;

    @Column
    @JsonView(DataTablesOutput.View.class)
    private String name;

    @Column(columnDefinition = "TEXT")
    @JsonView(DataTablesOutput.View.class)
    private String uzbek;

    @Column(columnDefinition = "TEXT")
    @JsonView(DataTablesOutput.View.class)
    private String russian;

    @Column(columnDefinition = "TEXT")
    @JsonView(DataTablesOutput.View.class)
    private String english;

    public void merge(Translation other) {
        this.id = other.id != null ? other.id : this.id;
        this.name = other.name != null ? other.name : this.name;
        this.uzbek = other.uzbek != null ? other.uzbek : this.uzbek;
        this.russian = other.russian != null ? other.russian : this.russian;
        this.english = other.english != null ? other.english : this.english;
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

    public String getUzbek() {
        return uzbek;
    }

    public void setUzbek(String uzbek) {
        this.uzbek = uzbek;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
