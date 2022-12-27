package pl.meating.meatingback.order;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meating.meatingback.jwt.JwtTokenUtil;
import pl.meating.meatingback.product.Product;
import pl.meating.meatingback.product.ProductDto;
import pl.meating.meatingback.product.ProductRepository;
import pl.meating.meatingback.user.UserRepository;
import pl.meating.meatingback.user.userdetails.UserInformation;
import pl.meating.meatingback.user.userdetails.UserInformationRepository;
import pl.meating.meatingback.user.userdetails.UserInformationService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final UserInformationService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Transactional
    public OrderDto addOrder(OrderDto orderDto,String token){
        orderDto.getProductList()
                .forEach(this::decrease);
        Order order=orderMapper.mapDtoToOrder(orderDto);
        getUserInformationFromToken(token);
        orderRepository.save(order);
        return orderDto;
    }

    @Transactional
    public OrderDto addOrderFromUnregisteredUser(UnregisteredUserOrder orderAndUser){

            orderAndUser.getProductList()
                    .forEach(this::decrease);
            OrderDto orderDto=orderMapper.getOrder(orderAndUser);
            Order order=orderMapper.mapDtoToOrder(orderDto);
            order.setUserInformation(orderMapper.getUserInformation(orderAndUser));
            orderRepository.save(order);
            return checkOrder(orderDto);

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

    private UserInformation getUserInformationFromToken(String token){
        return userInformationRepository.getByEmail(jwtTokenUtil.getUsernameFromToken(token)).orElseThrow(NoSuchElementException::new);
    }
}
