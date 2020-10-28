package vn.automation.sunweb.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String password;
    private String name;
    private String datecreated;
    private String phone;
    private String email;
    private String role;
    private Integer stt;
}
