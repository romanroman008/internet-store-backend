package pl.meating.meatingback.product;

import org.springframework.stereotype.Service;
import pl.meating.meatingback.order.Order;
import pl.meating.meatingback.order.OrderedProduct;

@Service
public class ProductMapper {
    private ProductRepository productRepository;

    public static ProductDto mapProductToDto(Product product){
        ProductDto dto=new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setAmount(product.getAmount());
        return dto;
    }
    public static Product mapDtoToProduct(ProductDto dto){
        Product product=new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        return product;
}
    public static OrderedProduct mapDtoToOrderedProduct(ProductDto dto) {
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setName(dto.getName());
        orderedProduct.setAmount(dto.getAmount());

        return orderedProduct;
    }
}
