package pl.meating.meatingback.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meating.meatingback.product.ProductDto;
import pl.meating.meatingback.product.ProductService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        ProductDto toReturn=productService.addProduct(productDto);
        return new ResponseEntity<ProductDto>(toReturn, HttpStatus.CREATED);
    }

    @GetMapping("getallproducts")
    public List<ProductDto> getAProducts(){
        return productService.getAll();
    }

    @PostMapping("delete")
    public boolean deleteProduct(String name){
        return productService.deleteProduct(name);
    }

}
