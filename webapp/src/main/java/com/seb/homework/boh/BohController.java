package com.seb.homework.boh;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import seb.homework.productselector.ProductMvcController;
import seb.homework.productselector.product.find.FindProductDto;
import seb.homework.productselector.product.find.FindProductJdbcAdapter;

@RestController
@RequiredArgsConstructor
public class BohController {

   private final FindProductJdbcAdapter adapter;

   private final ProductMvcController productMvcController;


   @GetMapping("/boh")
   public String boh() {
      return adapter.findAll().stream().map(FindProductDto::toString).reduce("", (a,b)-> a+ b);
   }
}
