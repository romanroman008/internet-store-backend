package pl.meating.meatingback.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.stereotype.Service;
import pl.meating.meatingback.product.Product;
import pl.meating.meatingback.product.ProductDto;
import pl.meating.meatingback.product.ProductMapper;
import pl.meating.meatingback.product.ProductRepository;
import pl.meating.meatingback.user.UserRepository;
import pl.meating.meatingback.user.userdetails.UserDetailsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final ProductRepository productRepository;

    public OrderDto mapOrderToDto(Order order){
        OrderDto dto=new OrderDto();
        dto.setDate(order.getDate());
        List<ProductDto> productList = order.getProductList()
                   .stream()
                   .map(ProductMapper::mapProductToDto)
                .collect(Collectors.toList());
        dto.setProductList(productList);
       // dto.setOwnerLogin(order.userLogin());
        dto.setOwnerEmail(order.getUserDetails().getEmail());
        return dto;
    }
    public Order mapDtoToOrder(OrderDto dto){
        Order order=new Order();
        order.setDate(dto.getDate());
        List<Product> productList=dto.getProductList()
                .stream()
                .map(ProductMapper::mapDtoToProduct)
                .collect(Collectors.toList());

        order.setProductList(productList);
        order.setUserDetails(userDetailsRepository.getByEmail(dto.getOwnerEmail()).get());
        //order.setUser(userRepository.findByUsername(dto.getOwnerLogin()).get());
        return order;
    }
}
