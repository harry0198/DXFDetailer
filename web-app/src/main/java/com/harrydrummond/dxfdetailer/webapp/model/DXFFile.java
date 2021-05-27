package com.harrydrummond.dxfdetailer.webapp.model;

import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class DXFFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lob annotation defines potentially large data
    @Lob
    private byte[] content;

    private String name;

    public DXFFile() {}

    public DXFFile(Long id, byte[] content, String name) {
        this.id = id;
        this.content = content;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}