package pl.meating.meatingback.product;



import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto addProduct(ProductDto dto){
        Product product = productMapper.mapDtoToProduct(dto);
            return productMapper.mapProductToDto(productRepository.save(product));
    }

    public boolean deleteProduct(String name){
        return productRepository.deleteByName(name);
    }

    public List<ProductDto> getAll(){
        return productRepository.findAll().stream().map(ProductMapper::mapProductToDto).collect(Collectors.toList());
    }
}
