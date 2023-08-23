package seb.homework.productselector.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seb.homework.productselector.core.product.CustomerInfoDto;
import seb.homework.productselector.core.product.verifier.ProductFinder;
import org.springframework.ui.Model;

import java.util.List;

@RestController
public class ProductController {

   private final ProductFinder productFinder;

   public ProductController(ProductFinder productFinder) {
      this.productFinder = productFinder;
   }

   @PostMapping("/recommend")
   public List<String> recommendProducts(@RequestBody CustomerInput dto) {

      return productFinder.getProducts(CustomerInfoDto.builder()
                  .age(dto.getAge())
                  .income(dto.getIncome())
                  .student(dto.isStudent())
            .build());
   }






}
