package seb.homework.productselector.verifier;

import lombok.RequiredArgsConstructor;
import seb.homework.productselector.product.CustomerInfoDto;
import seb.homework.productselector.product.Product;
import seb.homework.productselector.product.find.FindProductDto;
import seb.homework.productselector.product.find.FindProductUseCase;

import java.util.List;

import static seb.homework.productselector.validation.ValidationUtils.assertNotNegativeInteger;
import static seb.homework.productselector.validation.ValidationUtils.notNull;

@RequiredArgsConstructor
public class ProductsSelectionAdviser implements SelectProductsUseCase {

   private final FindProductUseCase port;

   @Override
   public List<String> getProducts(CustomerInfoDto dto) {
      notNull(dto, "customerInfoDto", "CustomerInfoDto is null");
      notNull(dto.getAge(), "customerInfoDto.age", "Age is null");
      assertNotNegativeInteger(dto.getAge(), "customerInfoDto.age", "Age must be positive");
      notNull(dto.getIncome(), "customerInfoDto.income", "Income is null");
      assertNotNegativeInteger(dto.getIncome(), "customerInfoDto.income", "Income must be non negative");

      List<FindProductDto> products = port.findAll();

      return products.stream()
            .map(Product::from)
            .filter(p -> p.isUserEligible(dto))
            .map(Product::getName)
            .toList();
   }
}