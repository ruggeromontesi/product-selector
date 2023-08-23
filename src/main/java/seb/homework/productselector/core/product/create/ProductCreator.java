package seb.homework.productselector.core.product.create;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductCreator implements CreateProductUseCase {

   private final CreateProductPort createProductPort;


   @Override
   public void create(CreateProductDto product) {
      createProductPort.create(product);
   }
}
