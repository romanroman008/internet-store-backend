package pl.meating.meatingback.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meating.meatingback.user.UserRole;
import pl.meating.meatingback.validators.TotalNumber;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<String> roles = new HashSet<>();
    public RegisterRequest(String login, String password, String firstName, String lastName, Date birthday, String street, int streetNumber, String city, String country, String phone, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }
}
