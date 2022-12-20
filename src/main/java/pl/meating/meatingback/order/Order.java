package pl.meating.meatingback.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meating.meatingback.user.userdetails.UserInformation;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
//    @ManyToMany
//    private List<Product> productList;
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="user_login")
//    private UserDao user;
//    @JsonProperty
//    String userLogin(){
//        return user.getUsername();
//    }
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedProduct> productList;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserInformation userInformation;






}
