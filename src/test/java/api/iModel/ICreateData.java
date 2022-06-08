package api.iModel;

import api.model.CreateData;

import java.util.Date;

public interface ICreateData {

     CreateData setName(String name);
     CreateData setJob(String job);
     CreateData setId(String id);
     Date setCreatedAt(Date createdAt);
     CreateData build();

}
