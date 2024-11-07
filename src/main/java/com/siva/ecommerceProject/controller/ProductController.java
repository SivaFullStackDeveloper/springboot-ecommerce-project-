package com.siva.ecommerceProject.controller;
import com.siva.ecommerceProject.model.Product;
import com.siva.ecommerceProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @PostMapping(value = "/saveProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveProduct(@RequestPart("product") Product product, @RequestPart("imageFile") MultipartFile imageFile) {
try{
    Product newProduct = productService.saveProduct(product,imageFile);
    return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
} catch (Exception e) {

    return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}

    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if(product != null)
            return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }


    @GetMapping("/products/{productId}/image")
    public ResponseEntity<?> getImageUrl(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        byte[]  imageData = product.getImageData();
        return  ResponseEntity.ok().body(imageData);
    }

    @GetMapping("/products/searchProduct")
    public ResponseEntity<?> searchProduct(@RequestParam String keyword) {
        List<Product> product = productService.searchProduct(keyword);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
