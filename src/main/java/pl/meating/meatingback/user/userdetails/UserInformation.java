package pl.meating.meatingback.user.userdetails;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.meating.meatingback.order.Order;
import pl.meating.meatingback.user.UserDao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {
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
    @Email
    private String email;
    @OneToOne(mappedBy = "userInformation")
    private UserDao userDao;
    @OneToMany(mappedBy = "userInformation")
    private List<Order> orderList;

    public void addOrder(Order order){
        this.orderList.add(order);
    }
}
