package api.model;

import api.iModel.ICreateData;

import java.util.Date;

public class CreateData implements ICreateData {

    private String name;
    private String job;
    private String id;
    private Date createdAt;

    public CreateData() {
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public CreateData setName(String name) {
        this.name = name;
        return this;
    }

    public CreateData setJob(String job) {
        this.job = job;
        return this;
    }

    public CreateData setId(String id) {
        this.id = id;
        return this;
    }

    public Date setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return createdAt;
    }

    @Override
    public CreateData build() {
        return this;
    }
}
