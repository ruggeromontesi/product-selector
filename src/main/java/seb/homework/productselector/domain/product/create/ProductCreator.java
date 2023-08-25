package seb.homework.productselector.domain.product.create;

import lombok.RequiredArgsConstructor;

import static seb.homework.productselector.domain.validation.ValidationUtils.assertBiggerThan;
import static seb.homework.productselector.domain.validation.ValidationUtils.assertNotNegativeInteger;
import static seb.homework.productselector.domain.validation.ValidationUtils.notEmpty;
import static seb.homework.productselector.domain.validation.ValidationUtils.notNull;

@RequiredArgsConstructor
public class ProductCreator implements CreateProductUseCase {

   private final CreateProductPort createProductPort;

   @Override
   public void create(CreateProductDto product) {
      notNull(product, "product", "Product cannot be null");
      notEmpty(product.getName(), "productName", "Product name cannot be empty or null");
      assertNotNegativeInteger(product.getMinAge(), "product.minAge", "Product minimum age cannot be negative");
      assertNotNegativeInteger(product.getMaxAge(), "product.maxAge", "Product maximum age cannot be negative");
      assertBiggerThan(product.getMaxAge(), product.getMinAge(), "product.maxAge", "product.minAge");
      assertNotNegativeInteger(product.getMinIncome(), "product.minIncome", "Product minimum income cannot be negative");
      assertNotNegativeInteger(product.getMaxIncome(), "product.maxIncome", "Product maximum income cannot be negative");
      assertBiggerThan(product.getMaxIncome(), product.getMinIncome(), "product.maxIncome", "product.minIncome");
      createProductPort.create(product);
   }
}
