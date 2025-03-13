package com.crjoua.projerp.services;

import com.crjoua.projerp.models.product.Product;
import com.crjoua.projerp.models.product.ProductDTO;
import com.crjoua.projerp.repositories.ProductRepository;
import com.crjoua.projerp.services.impls.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    @Autowired
    private ProductService productService;

    @Test
    public void mustFindById() {
        Product product = new Product().withId(1L).withName("product1").withDescription("desc1");
        when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO actual = this.productService.findById(1L);

        verify(this.productRepository, times(1)).findById(1L);
        assertEquals(product.getId(), actual.id());
        assertEquals(product.getName(), actual.name());
        assertEquals(product.getDescription(), actual.description());

    }

    @Test
    public void cantFindById() {
        when(this.productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> this.productService.findById(1L));
        verify(this.productRepository, times(1)).findById(1L);
    }

    @Test
    public void findAll() {
        Product product1 = new Product().withId(1L).withName("product1").withDescription("desc1");
        Product product2 = new Product().withId(2L).withName("product2").withDescription("desc2");
        when(this.productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<ProductDTO> all = this.productService.findAll();

        verify(this.productRepository, times(1)).findAll();
        assertThat(all).hasSize(2).extracting(ProductDTO::name).containsExactlyInAnyOrder("product1", "product2");

    }

    @Test
    public void save() {
        ProductDTO productDTO = new ProductDTO(null, "product1", "desc1");
        Product product1 = new Product().withName("product1").withDescription("desc1");
        Product product1Saved = new Product().withId(1L).withName("product1").withDescription("desc1");

        when(this.productRepository.save(eq(product1))).thenReturn(product1Saved);

        ProductDTO actual = this.productService.saveNew(productDTO);

        verify(this.productRepository, times(1)).save(any(Product.class));
        assertEquals(productDTO.name(), actual.name());
        assertEquals(productDTO.description(), actual.description());


    }
}