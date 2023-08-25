package seb.homework.productselector.domain.verifier;

import seb.homework.productselector.domain.product.CustomerInfoDto;

import java.util.List;

public interface SelectProductsUseCase {
   List<String> getProducts(CustomerInfoDto dto);
}
