package seb.homework.productselector.product.find;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import seb.homework.productselector.ProductTestConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductTestConfiguration.class})
class FindProductJdbcAdapterTest {

   private static final String CURRENT_ACCOUNT = "Current Account";

   private static final String CURRENT_ACCOUNT_PLUS = "Current Account Plus";
   private static final String JUNIOR_SAVER_ACCOUNT = "Junior Saver Account";
   private static final String STUDENT_ACCOUNT = "Student Account";
   private static final String SENIOR_ACCOUNT = "Senior Account";
   private static final String DEBIT_CARD = "Debit Card";
   private static final String CREDIT_CARD = "Credit Card";
   private static final String GOLD_CREDIT_CARD = "Gold Credit Card";

   private FindProductJdbcAdapter findProductJdbcAdapter;

   @Autowired
   private NamedParameterJdbcTemplate jdbc;

   @BeforeEach
   void setUp() {
      findProductJdbcAdapter = new FindProductJdbcAdapter(jdbc);
   }

   @Test
   void should_findAll() {
      var result = findProductJdbcAdapter.findAll();
      assertThat(getListOfProductNames(result)).isEqualTo(
            List.of(
                  CURRENT_ACCOUNT,
                  CURRENT_ACCOUNT_PLUS,
                  JUNIOR_SAVER_ACCOUNT,
                  STUDENT_ACCOUNT,
                  SENIOR_ACCOUNT,
                  DEBIT_CARD,
                  CREDIT_CARD,
                  GOLD_CREDIT_CARD
            )
      );
   }

   @NotNull
   private static List<String> getListOfProductNames(List<FindProductDto> result) {
      return result.stream().map(FindProductDto::getName).toList();
   }
}