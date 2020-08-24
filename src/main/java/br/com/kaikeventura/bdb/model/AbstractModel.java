package br.com.kaikeventura.bdb.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractModel implements Serializable {

    private static final long serialVersionUID = 7937967387432949847L;

    @Id
    private String id;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    private Date lastModifiedDate;
}
