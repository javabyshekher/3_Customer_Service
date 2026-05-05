package in.ashokit.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerProfile {
    private  Long  phoneNumber;
    private  String username;
    private  String email;
    private  String id;
    private  String description;
    private  String validity;
    private List<Object[]> friendsContactNumbers;

}