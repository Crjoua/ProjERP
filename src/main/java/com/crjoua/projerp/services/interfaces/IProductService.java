package com.crjoua.projerp.services.interfaces;

import com.crjoua.projerp.models.product.ProductDTO;

import java.util.List;
import java.util.NoSuchElementException;

public interface IProductService {
    /**
     * Saves the new product
     *
     * @param productDTO dto to be saved
     * @return dto of saved product
     */
    ProductDTO saveNew(ProductDTO productDTO);

    /**
     * Finds the product with id or else throw not found error
     *
     * @param id product id
     * @return dto of found product
     * @throws NoSuchElementException in case there is no product with this id
     */
    ProductDTO findById(Long id) throws NoSuchElementException;

    /**
     * Finds all products
     *
     * @return List of products
     */
    List<ProductDTO> findAll();
}