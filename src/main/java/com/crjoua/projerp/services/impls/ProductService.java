package com.crjoua.projerp.services.impls;

import com.crjoua.projerp.models.product.Product;
import com.crjoua.projerp.models.product.ProductDTO;
import com.crjoua.projerp.repositories.ProductRepository;
import com.crjoua.projerp.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    private static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription());
    }

    @Override
    public ProductDTO saveNew(ProductDTO dto) {
        Product product =
                this.productRepository.save(new Product().withName(dto.name()).withDescription(dto.description()));
        return toDTO(product);
    }

    @Override
    public ProductDTO findById(Long id) throws NoSuchElementException {
        return this.productRepository.findById(id).map(ProductService::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found"));
    }

    @Override
    public List<ProductDTO> findAll() {
        return this.productRepository.findAll().stream().map(ProductService::toDTO).toList();
    }
}