package seb.homework.productselector.core.product.verifier;

import seb.homework.productselector.core.product.CustomerInfoDto;

import java.util.List;

public interface ProductsProviderUseCase {
   List<String> getProducts(CustomerInfoDto dto);
}
