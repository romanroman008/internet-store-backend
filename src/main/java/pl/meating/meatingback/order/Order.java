package pl.meating.meatingback.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meating.meatingback.product.Product;
import pl.meating.meatingback.user.userdetails.UserDetails;

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
    @ManyToMany
    private List<Product> productList;
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="user_login")
//    private User user;
//    @JsonProperty
//    String userLogin(){
//        return user.getUsername();
//    }

    @OneToOne(mappedBy="order")
    private UserDetails ownUserDetails;


    public void addProduct(Product product){
        this.productList.add(product);
    }
}
