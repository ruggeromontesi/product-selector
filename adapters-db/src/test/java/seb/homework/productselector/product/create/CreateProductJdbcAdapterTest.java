package seb.homework.productselector.product.create;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import seb.homework.productselector.ProductTestConfiguration;
import seb.homework.productselector.product.find.FindProductDto;
import seb.homework.productselector.product.find.FindProductJdbcAdapter;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductTestConfiguration.class})
@EnableAutoConfiguration
class CreateProductJdbcAdapterTest {

   private static final String TEST_PRODUCT = "test-product";
   private static final String TEST_PRODUCT_2 = "test_product_2";
   private static final int MIN_AGE = 12;
   private static final int MAX_AGE = 44;
   private static final int MIN_INCOME = 2000;
   private static final int MAX_INCOME = 120000;
   private static final boolean MUST_BE_STUDENT = false;
   private FindProductJdbcAdapter findProductJdbcAdapter;

   private CreateProductJdbcAdapter createProductJdbcAdapter;

   @Autowired
   private NamedParameterJdbcTemplate jdbc;

   @BeforeEach
   void setUp() {
      createProductJdbcAdapter = new CreateProductJdbcAdapter(jdbc);
      findProductJdbcAdapter = new FindProductJdbcAdapter(jdbc);
   }

   @Test
   void should_createProduct_whenOnlyTestName() {
      CreateProductDto dto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .build();

      createProductJdbcAdapter.create(dto);

      var result = findProductJdbcAdapter.findAll();
      assertThat(getListOfProductNames(result)).isNotEmpty().hasSize(1);
      tearDown(TEST_PRODUCT);
   }

   @NotNull
   private static List<String> getListOfProductNames(List<FindProductDto> result) {
      return result.stream().map(FindProductDto::getName).filter(s -> s.equals(TEST_PRODUCT)).toList();
   }

   @Test
   void should_createProduct_whenAllData() {
      CreateProductDto dto = CreateProductDto.builder()
            .name(TEST_PRODUCT_2)
            .minAge(MIN_AGE)
            .maxAge(MAX_AGE)
            .minIncome(MIN_INCOME)
            .maxIncome(MAX_INCOME)
            .mustBeStudent(MUST_BE_STUDENT)
            .build();

      createProductJdbcAdapter.create(dto);

      var result = findProductJdbcAdapter.findAll();
      assertThat(result.stream().filter(p -> p.getName().equals(TEST_PRODUCT_2)).findFirst()).isPresent()
            .satisfies(p1 -> {
               var product = p1.get();
               assertThat(product.getMinAge()).isEqualTo(MIN_AGE);
               assertThat(product.getMaxAge()).isEqualTo(MAX_AGE);
               assertThat(product.getMinIncome()).isEqualTo(MIN_INCOME);
               assertThat(product.getMaxIncome()).isEqualTo(MAX_INCOME);
               assertThat(product.getMustBeStudent()).isEqualTo(MUST_BE_STUDENT);
            });
      tearDown(TEST_PRODUCT_2);

   }

   void tearDown(String name) {


      String sql = " DELETE FROM PRODUCT where name = :name ";
      Map<String, Object> params = Map.of("name", name);

      jdbc.update(sql, new MapSqlParameterSource(params));
   }

}