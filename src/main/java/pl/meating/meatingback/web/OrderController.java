package pl.meating.meatingback.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meating.meatingback.order.OrderDto;
import pl.meating.meatingback.order.OrderService;
import pl.meating.meatingback.order.OrderWrapper;
import pl.meating.meatingback.user.userdetails.UserDetailsDto;
import pl.meating.meatingback.user.userdetails.UserDetailsService;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserDetailsService userDetailsService;

    @PostMapping("adddetails")
    public ResponseEntity<UserDetailsDto> sendDetails(@RequestBody UserDetailsDto userDetailsDto){
        UserDetailsDto toReturn=userDetailsService.saveUserDetails(userDetailsDto);
        return new ResponseEntity<UserDetailsDto>(toReturn, HttpStatus.CREATED);
    }

    @PostMapping("check")
    public ResponseEntity<OrderDto> checkOrder(@RequestBody OrderDto orderDto){
        OrderDto toReturn=orderService.checkOrder(orderDto);
        return new ResponseEntity<OrderDto>(toReturn, HttpStatus.CREATED);
    }
    @PostMapping("addorderuser")
    public ResponseEntity<OrderDto> addRegisterUserOrder(@RequestBody OrderDto orderDto,String username){
        OrderDto toReturn=orderService.addUserOrder(orderDto,username);
        return new ResponseEntity<OrderDto>(toReturn, HttpStatus.CREATED);
    }

    @PostMapping("addorder")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderWrapper orderWrapper){

        OrderDto toReturn=orderService.addOrder(orderWrapper.orderDto, orderWrapper.userDetailsDto);
        return new ResponseEntity<OrderDto>(toReturn, HttpStatus.CREATED);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getAll();
    }
}
