package com.xzl.authserver.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import static java.util.Arrays.asList;

@Data
@Entity
public class User implements Serializable{
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String username;

    private String password;
    private String email;
    private Date lastPasswordResetDate;
    private String roles;

    public List<String> genRolesArray() {
        return asList(roles.split("-"));
    }

    public void setRolesArray(List<String> list) {
        roles = "";
        for (String o : list) {
            roles += o + "-";
        }
    }
}
