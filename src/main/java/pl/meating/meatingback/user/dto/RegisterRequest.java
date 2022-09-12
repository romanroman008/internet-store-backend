package pl.meating.meatingback.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meating.meatingback.validators.TotalNumber;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String login;
    private String password;
    @Size(min=2)
    private String firstName;
    @Size(min=2)
    private String lastName;
    @Past
    private Date birthday;
    private String street;
    @Positive
    @TotalNumber
    private float streetNumber;
    @Positive
    @TotalNumber
    private float flatNumber;
    private String city;
    private String country;
    private String phone;
    @Email
    private String email;

}
