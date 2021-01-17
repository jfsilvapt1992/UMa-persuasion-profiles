package com.uma.mestrado.persuasive_profiles.database.entities;
// Generated Jan 17, 2021 10:36:05 AM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Historic generated by hbm2java
 */
@Entity
@Table(name="historic"
    ,catalog="persuasive_computation"
)
public class Historic  implements java.io.Serializable {


     private Integer id;
     private InfluencePrinciple influencePrinciple;
     private Person person;
     private Date date;
     private Date time;

    public Historic() {
    }

    public Historic(InfluencePrinciple influencePrinciple, Person person, Date date, Date time) {
       this.influencePrinciple = influencePrinciple;
       this.person = person;
       this.date = date;
       this.time = time;
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
    @JoinColumn(name="influence_principle_id", nullable=false)
    public InfluencePrinciple getInfluencePrinciple() {
        return this.influencePrinciple;
    }
    
    public void setInfluencePrinciple(InfluencePrinciple influencePrinciple) {
        this.influencePrinciple = influencePrinciple;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="person_id", nullable=false)
    public Person getPerson() {
        return this.person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date", nullable=false, length=10)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="time", nullable=false, length=8)
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }




}


