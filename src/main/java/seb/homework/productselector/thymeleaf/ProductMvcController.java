package seb.homework.productselector.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import seb.homework.productselector.domain.product.CustomerInfoDto;
import seb.homework.productselector.domain.verifier.ProductsSelectionAdviser;

import java.util.List;


@Controller
public class ProductMvcController {

   private final ProductsSelectionAdviser productFinder;

   public ProductMvcController(ProductsSelectionAdviser productFinder) {
      this.productFinder = productFinder;
   }

   @GetMapping("/form")
   public String fillForm(Model model) {

      CustomerInfoDto customer = CustomerInfoDto.builder()
            .income(0)
            .age(16)
            .build();
      model.addAttribute("customer", customer);
      model.addAttribute("products", List.of());

      return "recommendedProducts";
   }

   @GetMapping("/products/recommend")
   public String recommend(Model model, CustomerInfoDto customer) {
      List<String> suitableProducts = productFinder.getProducts(customer);
      model.addAttribute("products", suitableProducts);
      model.addAttribute("customer", customer);
      return "recommendedProducts";
   }
}
