package pl.meating.meatingback.user.userdetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private String firstName;
    private String lastName;
    @Past
    private Date birthday;
    private String street;
    private int streetNumber;
    private int flatNumber;
    private String city;
    private String country;
    private String phone;
    //@Email(message="to nie email")
    private String email;
}
