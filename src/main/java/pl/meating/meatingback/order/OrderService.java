package pl.meating.meatingback.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meating.meatingback.product.Product;
import pl.meating.meatingback.product.ProductDto;
import pl.meating.meatingback.product.ProductRepository;
import pl.meating.meatingback.user.UserRepository;
import pl.meating.meatingback.user.userdetails.UserDetails;
import pl.meating.meatingback.user.userdetails.UserDetailsDto;
import pl.meating.meatingback.user.userdetails.UserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
//
//    @Transactional
//    public OrderDto addOrder(OrderDto orderDto){
//        orderDto.getProductList()
//                .forEach(this::decrease);
////        User user=userRepository.getByLogin("JarekArek").get();
//        Order order=orderMapper.mapDtoToOrder(orderDto);
////        order.setUser(user);
////        user.addOrder(order);
//        orderRepository.save(order);
//        return orderDto;
//    }

    @Transactional
    public OrderDto addOrder(OrderDto orderDto){
        orderDto.getProductList()
                .forEach(this::decrease);
        Order order=orderMapper.mapDtoToOrder(orderDto);
        //userDetailsService.saveUserDetails(userDetailsDto);
        orderRepository.save(order);
        return orderDto;
    }

    @Transactional
    public OrderDto addUserOrder(OrderDto orderDto, String username){
        orderDto.getProductList()
                .forEach(this::decrease);
        UserDetails userDetails=userRepository.findByUsername(username).get().getUserDetails();
        Order order=orderMapper.mapDtoToOrder(orderDto);
        order.setUserDetails(userDetails);
        orderRepository.save(order);
        return orderDto;
    }



    public OrderDto checkOrder(OrderDto orderDto){
        orderDto.getProductList()
                .forEach(this::check);
        return orderDto;
    }

    private ProductDto check(ProductDto dto){
        Product product = productRepository.getByName(dto.getName()).get(); //>>
        if(product.getAmount()-dto.getAmount()>=0) {
            dto.setDescription("Produkt dostępny");
        }
        else{
            dto.setDescription("Brak tylu produktów w magazynie. Dostępna ilość sztuk: "+product.getAmount());
            dto.setAmount(product.getAmount());
        }
        return dto;
    }

    private ProductDto decrease(ProductDto dto){
        Product product = productRepository.getByName(dto.getName()).get(); //>>
        if(product.getAmount()-dto.getAmount()>=0) {
            product.setAmount(product.getAmount() - dto.getAmount());
            dto.setDescription("Udało się zamówić produkt");
        }
        else{
            dto.setDescription("Brak tylu produktów w magazynie. Dostępna ilość sztuk: "+product.getAmount());
            dto.setAmount(product.getAmount());
            product.setAmount(0);
        }
        productRepository.save(product);
        return dto;
    }

    public List<OrderDto> getAll(){
       return this.orderRepository.findAll().stream().map(orderMapper::mapOrderToDto).collect(Collectors.toList());
    }
}
