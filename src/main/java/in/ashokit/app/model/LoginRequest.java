package in.ashokit.app.model;

import lombok.Data;

@Data
public class LoginRequest {
    private Long phoneNumber;
    private String password;

}