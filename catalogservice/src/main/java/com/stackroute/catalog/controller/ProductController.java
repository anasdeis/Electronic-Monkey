package com.stackroute.catalog.controller;

import com.stackroute.catalog.exception.ProductAlreadyExistsException;
import com.stackroute.catalog.exception.ProductNotFoundException;
import com.stackroute.catalog.model.Product;
import com.stackroute.catalog.service.ProductService;
import com.stackroute.catalog.swagger.SpringFoxConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
@Api(tags = { SpringFoxConfig.PRODUCT_TAG })
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    @ApiOperation("Creates a new product.")
    public ResponseEntity<Product> saveProduct(@ApiParam("Product information for a new product to be created. 409 if already exists.") @RequestBody Product product) throws ProductAlreadyExistsException {
        log.info("Create a new product: " + product.toString());
        return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    @ApiOperation("Returns list of all products in the system.")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("Return list of all products in the system.");
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    @ApiOperation("Returns a specific product by their identifier. 404 if does not exist.")
    public ResponseEntity<Product> getProductById(@ApiParam("Id of the product to be obtained. Cannot be empty.")  @PathVariable String id) throws ProductNotFoundException {
        log.info("Return product with id = " + id);
        return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/products/code/{code}")
    @ApiOperation("Returns a specific product by their code. 404 if does not exist.")
    public ResponseEntity<Product> getProductByCode(@ApiParam("Code of the product to be obtained. Cannot be empty.") @PathVariable String code) throws ProductNotFoundException {
        log.info("Return product with code = " + code);
        return new ResponseEntity<Product>(productService.getProductByCode(code), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    @ApiOperation("Deletes a product from the system. 404 if the person's identifier is not found.")
    public ResponseEntity<Product> deleteProduct(@ApiParam("Id of the product to be deleted. Cannot be empty.") @PathVariable String id) throws ProductNotFoundException {
        log.info("Delete product with id = " + id);
        return new ResponseEntity<Product>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @PatchMapping("/products")
    @ApiOperation("Updates a new product.")
    public ResponseEntity<Product> updateProduct(@ApiParam("Product information for a product to be updated. 404 if does not exist.") @RequestBody Product product) throws ProductNotFoundException {
        log.info("Update product: " + product.toString());
        return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
    }
}