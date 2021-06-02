package com.firestudio.projectspacex.Modal;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "crewMemberTable", indices = @Index(value = {"id"}, unique = true))
public class CrewMember {
    @PrimaryKey(autoGenerate = true)
    private int autoId;

    private String name;
    private String agency;
    private String image;
    private String wikipedia;
    @Ignore
    private List<String> launches = null;
    private String status;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public List<String> getLaunches() {
        return launches;
    }

    public void setLaunches(List<String> launches) {
        this.launches = launches;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "autoId=" + autoId +
                ", name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                ", image='" + image + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", launches=" + launches +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}