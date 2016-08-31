package com.chyld.entities;

import com.chyld.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profiles")
@Data
public class Profile {
    private int id;
    private int age;
    private int weight;
    private int height;
    private String photo;
    private Gender gender;
    private int version;
    private Date created;
    private Date modified;
    private User userId;

    public Profile() {
        this.created = new Date();
        this.modified = new Date();
    }

    @Id
    @GeneratedValue
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Version
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    @Column(nullable = false)
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Column(nullable = false)
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    @Column(nullable = false)
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    @Column(nullable = false)
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('m', 'f')")
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    @Column(nullable = false, updatable = false)
    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    @Column(nullable = false)
    public Date getModified() { return modified; }
    public void setModified(Date modified) { this.modified = modified; }

//    @OneToOne( mappedBy = "profile" )
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore // to avoid circular references
    public User getUserId() { return userId; }
    public void setUserId(User userId) { this.userId = userId; }

    @PreUpdate
    protected void onUpdate() {
        this.modified = new Date();
    }
}
