package no.nyseth.fantasd.shopnuser;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class User {
    // Class containing the parameters of the users.

    //Userid
    String userid;

    //Email
    String email;

    //Name Fields
    String firstName;
    String lastName;

    Date creationDate;

}
