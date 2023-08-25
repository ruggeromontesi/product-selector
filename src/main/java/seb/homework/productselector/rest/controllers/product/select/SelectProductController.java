package seb.homework.productselector.rest.controllers.product.select;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seb.homework.productselector.domain.product.CustomerInfoDto;
import seb.homework.productselector.domain.verifier.ProductsSelectionAdviser;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SelectProductController {

   private final ProductsSelectionAdviser productFinder;

   @RequestMapping(value = {"/select/age/{age}/income/{income}/student/{student}", "/select/age/{age}/income/{income}"})
   public List<String> selectProducts(@PathVariable Map<String, String> pathVariablesMap) {
      return productFinder.getProducts(toDto(pathVariablesMap));
   }

   private static CustomerInfoDto toDto(Map<String, String> pathVariablesMap) {
      int age = Integer.parseInt(pathVariablesMap.get("age"));
      int income = Integer.parseInt(pathVariablesMap.get("income"));
      boolean student = Boolean.parseBoolean(pathVariablesMap.get("student"));

      return CustomerInfoDto.builder()
            .age(age)
            .income(income)
            .student(student)
            .build();
   }
}
