package com.crjoua.projerp.controllers;

import com.crjoua.projerp.models.product.ProductDTO;
import com.crjoua.projerp.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/products/new")
    public ProductDTO newProduct(@RequestBody ProductDTO product) {
        return this.productService.saveNew(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(this.productService.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.findById(id));
    }

}