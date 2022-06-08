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

    public CreateData(String name, String job, String id, Date createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public CreateData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }
}
