package com.uma.mestrado.persuasive_profiles.database.entities;
// Generated Jan 17, 2021 7:37:12 PM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Person generated by hbm2java
 */
@Entity
@Table(name="person"
    ,catalog="persuasive_computation"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class Person  implements java.io.Serializable {


     private Integer id;
     private Country country;
     private Gender gender;
     private String name;
     private Integer age;
     private Integer weight;
     private Integer height;
     private boolean hadNutricionalConsult;
     private Set<Historic> historics = new HashSet<Historic>(0);
     private Set<Users> userses = new HashSet<Users>(0);

    public Person() {
    }

	
    public Person(Country country, Gender gender, String name, Integer age, Integer weight, Integer height, boolean hadNutricionalConsult) {
        this.country = country;
        this.gender = gender;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.hadNutricionalConsult = hadNutricionalConsult;
    }
    public Person(Country country, Gender gender, String name, Integer age, Integer weight, Integer height, boolean hadNutricionalConsult, Set<Historic> historics, Set<Users> userses) {
       this.country = country;
       this.gender = gender;
       this.name = name;
       this.age = age;
       this.weight = weight;
       this.height = height;
       this.hadNutricionalConsult = hadNutricionalConsult;
       this.historics = historics;
       this.userses = userses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=false)
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gender_id", nullable=false)
    public Gender getGender() {
        return this.gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    
    @Column(name="name", unique=true, nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="age", nullable=false)
    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    
    @Column(name="weight", nullable=false)
    public Integer getWeight() {
        return this.weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    
    @Column(name="height", nullable=false)
    public Integer getHeight() {
        return this.height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }

    
    @Column(name="had_nutricional_consult", nullable=false)
    public boolean isHadNutricionalConsult() {
        return this.hadNutricionalConsult;
    }
    
    public void setHadNutricionalConsult(boolean hadNutricionalConsult) {
        this.hadNutricionalConsult = hadNutricionalConsult;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="person")
    public Set<Historic> getHistorics() {
        return this.historics;
    }
    
    public void setHistorics(Set<Historic> historics) {
        this.historics = historics;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="person")
    public Set<Users> getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set<Users> userses) {
        this.userses = userses;
    }




}


