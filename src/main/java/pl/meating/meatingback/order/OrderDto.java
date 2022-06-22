package pl.meating.meatingback.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meating.meatingback.product.ProductDto;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Date date;
    private List<ProductDto> productList;
    //private String ownerLogin;
    private String ownerEmail;
}
