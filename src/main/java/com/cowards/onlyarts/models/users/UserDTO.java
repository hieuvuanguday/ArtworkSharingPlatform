package com.cowards.onlyarts.models.users;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    
    private String userId;
    private String roleId;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private Date joinDate;
    private String bio;
    private int status;
    private String password;
}
