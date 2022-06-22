package pl.meating.meatingback.user.userdetails;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.meating.meatingback.order.Order;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Email(message="to nie email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_table")
   private Order order;
}
