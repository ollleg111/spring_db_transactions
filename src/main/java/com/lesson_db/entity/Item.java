package com.lesson_db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ITEMS")
public class Item {

    /*
    CREATE TABLE ITEMS(
    ITEM_ID NUMBER,
    CONSTRAINT ITEM_PK PRIMARY KEY(ITEM_ID),
    NAME NVARCHAR2(50) NOT NULL,
    DATE_CREATED TIMESTAMP,
    DATE_UPDATED TIMESTAMP,
    DESCRIPTION NVARCHAR2(100) NOT NULL
    );

    CREATE SEQUENCE ITEMS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
    DROP SEQUENCE ITEMS_SEQ;
    */

    private long id;
    private String name;
    private Date dateCreated;
    private Date lastUpdateDate;
    private String description;

    public Item() {
    }

    @Id
    @SequenceGenerator(name = "ITEMS_SEQ", sequenceName = "ITEMS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMS_SEQ")
    @Column(name = "ITEM_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return dateCreated;
    }

    @Column(name = "DATE_UPDATED")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdateDate=" + lastUpdateDate +
                ", description='" + description + '\'' +
                '}';
    }
}
