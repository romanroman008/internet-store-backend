package pl.meating.meatingback.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meating.meatingback.order.OrderDto;
import pl.meating.meatingback.order.OrderService;
import pl.meating.meatingback.order.UnregisteredUserOrder;
import pl.meating.meatingback.user.userdetails.UserInformationDto;
import pl.meating.meatingback.user.userdetails.UserInformationService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final UserInformationService userDetailsService;




    @PostMapping("adddetails")
    public ResponseEntity<UserInformationDto> sendDetails(@RequestBody UserInformationDto userInformationDto){
        UserInformationDto toReturn=userDetailsService.saveUserDetails(userInformationDto);
        return new ResponseEntity<UserInformationDto>(toReturn, HttpStatus.CREATED);
    }

    @PostMapping("check")
    public ResponseEntity<OrderDto> checkOrder(@RequestBody OrderDto orderDto){
        OrderDto toReturn=orderService.checkOrder(orderDto);

        return new ResponseEntity<OrderDto>(toReturn, HttpStatus.CREATED);
    }
    @PostMapping("addorderwithinfo")
    public ResponseEntity<OrderDto> addRegisterUserOrder(@RequestBody UnregisteredUserOrder orderDto){
        OrderDto toReturn=orderService.addOrderFromUnregisteredUser(orderDto);
        return new ResponseEntity<OrderDto>(toReturn, HttpStatus.CREATED);
    }

    @PostMapping("addorder")
    public ResponseEntity<?> addOrder(@RequestHeader("Authorization") String token,@RequestBody OrderDto orderDto){
        try{
            OrderDto toReturn=orderService.addOrder(orderDto,token);
            return new ResponseEntity<OrderDto>(toReturn, HttpStatus.CREATED);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getAll();
    }
}
