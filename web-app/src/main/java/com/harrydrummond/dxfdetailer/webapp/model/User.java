package com.harrydrummond.dxfdetailer.webapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

//https://www.mysqltutorial.org/mysql-uuid/
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "date_created")
    private Date dateCreated;

    public User() { }

    public User(UUID userId, String emailAddress, Date dateCreated) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.dateCreated = dateCreated;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}