package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Transient
    public static final String sequenceName = "users_id_seq";

    @Id
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @JsonView(DataTablesOutput.View.class)
    private Long id;

    @NotNull
    @NotEmpty
    @JsonView(DataTablesOutput.View.class)
    private String username;

    @NotNull
    @NotEmpty
    @JsonView(DataTablesOutput.View.class)
    private String password;

    @JsonView(DataTablesOutput.View.class)
    private Boolean enabled;

    @JsonView(DataTablesOutput.View.class)
    private Boolean deleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(DataTablesOutput.View.class)
    private Set<UserRole> userRoles;

    @JsonView(DataTablesOutput.View.class)
    private String fullname;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(DataTablesOutput.View.class)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(DataTablesOutput.View.class)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(DataTablesOutput.View.class)
    private Date lastVisit;

    @JsonView(DataTablesOutput.View.class)
    private Long createdBy;

    @JsonView(DataTablesOutput.View.class)
    private Long updatedBy;

    @Transient
    private String roleName;

    @PrePersist
    public void onCreate() {
        setEnabled(Boolean.TRUE);
        setDeleted(Boolean.FALSE);
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }

    @PreUpdate
    public void onUpdate() {
        setUpdatedAt(new Date());
    }

    public boolean exist() {
        return this.id != null;
    }

    public void merge(User other) {
        this.id = other.id != null ? other.id : this.id;
        this.username = other.username != null ? other.username : this.username;
        this.password = other.password != null ? other.password : this.password;
        this.enabled = other.enabled != null ? other.enabled : this.enabled;
        this.deleted = other.deleted != null ? other.deleted : this.deleted;
        this.fullname = other.fullname != null ? other.fullname : this.fullname;
        this.createdAt = other.createdAt != null ? other.createdAt : this.createdAt;
        this.updatedAt = other.updatedAt != null ? other.updatedAt : this.updatedAt;
        this.lastVisit = other.lastVisit != null ? other.lastVisit : this.lastVisit;
        this.createdBy = other.createdBy != null ? other.createdBy : this.createdBy;
        this.updatedBy = other.updatedBy != null ? other.updatedBy : this.updatedBy;
        this.userRoles = other.userRoles != null ? other.userRoles : this.userRoles;
        this.roleName = other.roleName != null ? other.roleName : this.roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
