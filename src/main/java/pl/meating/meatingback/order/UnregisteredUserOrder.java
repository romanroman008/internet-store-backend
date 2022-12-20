package pl.meating.meatingback.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meating.meatingback.product.ProductDto;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnregisteredUserOrder {
    private String firstName;
    private String lastName;
    //@Past
    private Date birthday;
    private String street;
    private int streetNumber;
    private int flatNumber;
    private String city;
    private String country;
    private String phone;
    //@Email(message="to nie email")
    private String email;

    private Date date;
    private List<ProductDto> productList;
}
