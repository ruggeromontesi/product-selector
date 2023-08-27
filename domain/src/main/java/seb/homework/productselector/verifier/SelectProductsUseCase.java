package seb.homework.productselector.verifier;

import seb.homework.productselector.product.CustomerInfoDto;

import java.util.List;

public interface SelectProductsUseCase {
   List<String> getProducts(CustomerInfoDto dto);
}
