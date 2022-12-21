package pl.meating.meatingback.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meating.meatingback.jwt.JwtTokenUtil;
import pl.meating.meatingback.product.Product;
import pl.meating.meatingback.product.ProductDto;
import pl.meating.meatingback.product.ProductMapper;
import pl.meating.meatingback.product.ProductRepository;
import pl.meating.meatingback.user.UserRepository;
import pl.meating.meatingback.user.userdetails.UserInformation;
import pl.meating.meatingback.user.userdetails.UserInformationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;
    private final ProductRepository productRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public OrderDto mapOrderToDto(Order order){
        OrderDto dto=new OrderDto();
//        dto.setDate(order.getDate());
//        List<ProductDto> productList = order.getProductList()
//                   .stream()
//                   .map(ProductMapper::mapProductToDto)
//                .collect(Collectors.toList());
//        dto.setProductList(productList);
//       // dto.setOwnerLogin(order.userLogin());
//        //dto.setJwt(order.getUserInformation().getEmail());
        return dto;
    }
    public Order mapDtoToOrder(OrderDto dto){
        Order order=new Order();
        order.setDate(dto.getDate());
        List<OrderedProduct> productList=dto.getProductList()
                .stream()
                .map(ProductMapper::mapDtoToOrderedProduct)
                .collect(Collectors.toList());

        order.setProductList(productList);
        if(dto.getJwt()!=null)
        {
            String email=jwtTokenUtil.getUsernameFromToken(dto.getJwt());
            order.setUserInformation(this.userInformationRepository.getByEmail(email).get());
        }

        //order.setUserDao(userRepository.findByUsername(dto.getOwnerLogin()).get());
        return order;
    }

    public OrderDto getOrder(UnregisteredUserOrder dto){
        OrderDto order=new OrderDto();
        order.setDate(dto.getDate());
//        List<OrderedProduct> productList=dto.getProductList()
//                .stream()
//                .map(ProductMapper::mapDtoToOrderedProduct)
//                .collect(Collectors.toList());
        order.setProductList(dto.getProductList());

        return order;
    }

    public UserInformation getUserInformation(UnregisteredUserOrder dto){
        UserInformation userInformation=new UserInformation();
        userInformation.setFirstName(dto.getFirstName());
        userInformation.setLastName(dto.getLastName());
        userInformation.setBirthday(dto.getBirthday());
        userInformation.setStreet(dto.getStreet());
        userInformation.setStreetNumber((int) dto.getStreetNumber());
        userInformation.setFlatNumber((int) dto.getFlatNumber());
        userInformation.setCity(dto.getCity());
        userInformation.setCountry(dto.getCountry());
        userInformation.setPhone(dto.getPhone());
        userInformation.setEmail(dto.getEmail());
        return userInformation;
    }



   private UserInformation geUserInformationFromToken(String jwt){
//        if(userInformationRepository.getByEmail(jwt).isPresent()){
//            return userInformationRepository.getByEmail(jwt).get();
//        }
        if(!jwtTokenUtil.getUsernameFromToken(jwt).isEmpty()){
            return userInformationRepository.getByEmail(jwtTokenUtil.getUsernameFromToken(jwt)).get();
        }
        throw new NoSuchElementException("Błąd emaila");
    }
}
