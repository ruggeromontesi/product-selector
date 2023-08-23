package seb.homework.productselector.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import seb.homework.productselector.core.product.CustomerInfoDto;
import seb.homework.productselector.core.product.verifier.ProductFinder;


@Controller
public class ProductMvcController {

   private final ProductFinder productFinder;

   public ProductMvcController(ProductFinder productFinder) {
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
      var a = productFinder.getProducts(customer);
      return "redirect:/books";
   }



}
