package seb.homework.productselector.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

      CustomerInfoDto customer = CustomerInfoDto.builder().build();
      model.addAttribute("customer", customer);

      return "customerForm";
   }


   @PostMapping("/products/recommend")
   public String recommend(Model model, CustomerInfoDto customer) {
      List<String> suitableProducts = productFinder.getProducts(customer);
      model.addAttribute("products", suitableProducts);
      model.addAttribute("customer", customer);
      return "formAndRecommendedProducts";
   }



}
