package pl.meating.meatingback.user;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.meating.meatingback.order.Order;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
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
    @Email(message = "jebac")
    private String email;
//    @OneToMany(cascade = CascadeType.ALL,mappedBy="user")
//    private List<Order> orderList;

    public User(String username, String password, String firstName, String lastName, Date birthday, String street, int streetNumber, int flatNumber, String city, String country, String phone, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )


    private Set<UserRole> roles = new HashSet<>();

//    public void addOrder(Order order){
//        this.orderList.add(order);
//    }
}
