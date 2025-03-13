package com.crjoua.projerp.integration;

import com.crjoua.projerp.models.product.ProductDTO;
import com.crjoua.projerp.services.impls.ProductService;
import liquibase.exception.DatabaseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceAndRepositoryTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void tearDown() throws DatabaseException {
        JdbcTestUtils.deleteFromTables(this.jdbcTemplate, "PRODUCT");
    }

    @Test
    public void saveOne() {
        ProductDTO dtoToSave = new ProductDTO(null, "product1", "description1");
        ProductDTO savedProduct = this.productService.saveNew(dtoToSave);

        assertEquals(dtoToSave.name(), savedProduct.name());
        assertEquals(dtoToSave.description(), savedProduct.description());

    }

    @Test
    public void saveAndRetrieveOneById() {
        ProductDTO dtoToSave = new ProductDTO(null, "product1", "description1");
        ProductDTO savedProduct = this.productService.saveNew(dtoToSave);

        assertEquals(dtoToSave.name(), savedProduct.name());
        assertEquals(dtoToSave.description(), savedProduct.description());

        ProductDTO foundById = this.productService.findById(savedProduct.id());
        assertEquals(savedProduct, foundById);
    }

    @Test
    public void nameUniqueTest() {
        ProductDTO dtoToSave = new ProductDTO(null, "product1", "description1");
        this.productService.saveNew(dtoToSave);

        ProductDTO dtoToSaveDuplicated = new ProductDTO(null, "product1", "description2");
        assertThrows(DataIntegrityViolationException.class, () -> this.productService.saveNew(dtoToSaveDuplicated));
    }

    @Test
    public void saveAndRetrieveMultiple() {
        ProductDTO dtoToSave1 = new ProductDTO(null, "product1", "description1");
        ProductDTO savedProduct1 = this.productService.saveNew(dtoToSave1);
        ProductDTO dtoToSave2 = new ProductDTO(null, "product2", "description2");
        ProductDTO savedProduct2 = this.productService.saveNew(dtoToSave2);

        List<ProductDTO> all = this.productService.findAll();

        assertThat(all).hasSize(2).extracting(ProductDTO::name).
                containsExactlyInAnyOrder(savedProduct1.name(), savedProduct2.name());

    }

}