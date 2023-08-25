package seb.homework.productselector.rest.controllers.product.create;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import seb.homework.productselector.domain.product.create.CreateProductDto;
import seb.homework.productselector.domain.product.create.CreateProductUseCase;

@RestController
@RequiredArgsConstructor
public class CreateProductController {

   private final CreateProductUseCase productCreator;

   @PostMapping("/product/create")
   @ResponseStatus(HttpStatus.CREATED)
   public void createProduct(@RequestBody CreateProductDto createProductDto) {
      productCreator.create(createProductDto);
   }
}
