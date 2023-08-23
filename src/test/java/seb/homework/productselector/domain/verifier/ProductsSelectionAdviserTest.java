package seb.homework.productselector.domain.verifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seb.homework.productselector.domain.product.CustomerInfoDto;
import seb.homework.productselector.domain.product.find.FindProductDto;
import seb.homework.productselector.db.product.find.FindProductJdbcAdapter;
import seb.homework.productselector.domain.product.find.FindProductUseCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsSelectionAdviserTest {
   private static final String CURRENT_ACCOUNT = "Current Account";
   private static final FindProductDto CURRENT_ACCOUNT_PRODUCT = FindProductDto.builder().name(CURRENT_ACCOUNT).minIncome(0).minAge(17).build();
   private static final String CURRENT_ACCOUNT_PLUS = "Current Account Plus";
   private static final FindProductDto CURRENT_ACCOUNT_PLUS_PRODUCT = FindProductDto.builder().name(CURRENT_ACCOUNT_PLUS).minIncome(40000).minAge(17).build();
   private static final String JUNIOR_SAVER_ACCOUNT = "Junior Saver Account";
   private static final FindProductDto JUNIOR_SAVER_ACCOUNT_PRODUCT = FindProductDto.builder().name(JUNIOR_SAVER_ACCOUNT).maxAge(18).build();
   private static final String STUDENT_ACCOUNT = "Student Account";
   private static final FindProductDto STUDENT_ACCOUNT_PRODUCT = FindProductDto.builder().name(STUDENT_ACCOUNT).minAge(17).mustBeStudent(true).build();
   private static final String SENIOR_ACCOUNT = "Senior Account";
   private static final FindProductDto SENIOR_ACCOUNT_PRODUCT = FindProductDto.builder().name(SENIOR_ACCOUNT).minAge(64).build();
   private static final String DEBIT_CARD = "Debit Card";
   private static final FindProductDto DEBIT_CARD_PRODUCT = FindProductDto.builder().name(DEBIT_CARD).maxIncome(12001).minAge(17).build();
   private static final String CREDIT_CARD = "Credit Card";
   private static final FindProductDto CREDIT_CARD_PRODUCT = FindProductDto.builder().name(CREDIT_CARD).minIncome(12000).minAge(17).build();
   private static final String GOLD_CREDIT_CARD = "Gold Credit Card";
   private static final FindProductDto GOLD_CREDIT_CARD_PRODUCT = FindProductDto.builder().name(GOLD_CREDIT_CARD).minIncome(40000).minAge(17).build();

   @InjectMocks
   private ProductsSelectionAdviser productFinder;

   @Mock
   private FindProductJdbcAdapter adapter;

   @Mock
   private FindProductUseCase findProductUseCase;

   @BeforeEach
   void init() {
      when(findProductUseCase.findAll()).thenReturn(List.of(
            CURRENT_ACCOUNT_PRODUCT,
            CURRENT_ACCOUNT_PLUS_PRODUCT,
            JUNIOR_SAVER_ACCOUNT_PRODUCT,
            STUDENT_ACCOUNT_PRODUCT,
            SENIOR_ACCOUNT_PRODUCT,
            DEBIT_CARD_PRODUCT,
            CREDIT_CARD_PRODUCT,
            GOLD_CREDIT_CARD_PRODUCT
      ));
   }

   @ParameterizedTest
   @ValueSource(ints = {1, 12000, 40000, 40001})
   void should_returnOnlyJuniorSaverAccount_whenAgeLessThan17(int income) {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(17)
            .income(income)
            .build());

      assertThat(result).isEqualTo(List.of(JUNIOR_SAVER_ACCOUNT));
   }

   @Test
   void should_return_OnlyDebitCard_whenAgeBetween18nd64AndNoIncome() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(27)
            .income(0)
            .build());

      assertThat(result).isEqualTo(List.of(DEBIT_CARD));
   }

   @Test
   void should_returnDebitCardAndCurrentAccount_whenAgeBetween18nd64AndIncomeBetween1And12k() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(27)
            .income(550)
            .build());

      assertThat(result).isEqualTo(List.of(CURRENT_ACCOUNT, DEBIT_CARD));
   }


   @Test
   void should_returnCurrentAccountAndCreditCard_whenAgeBetween18nd64AndIncomeBetween12kAnd40k() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(27)
            .income(30000)
            .build());

      assertThat(result).isEqualTo(List.of(CURRENT_ACCOUNT, CREDIT_CARD));
   }

   @Test
   void should_returnCurrentAccountAndCreditCardAndCurrentAccountPlusAndGoldenCreditCard_whenAgeBetween18nd64AndIncomeMoreThan40001k() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(27)
            .income(430000)
            .build());

      assertThat(result).isEqualTo(List.of(CURRENT_ACCOUNT, CURRENT_ACCOUNT_PLUS, CREDIT_CARD, GOLD_CREDIT_CARD));
   }

   @Test
   void should_seniorAccountAndDebitCard_whenAgeMoreThan64AndNoIncome() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(67)
            .income(0)
            .build());

      assertThat(result).isEqualTo(List.of(SENIOR_ACCOUNT, DEBIT_CARD));
   }

   @Test
   void should_return_currentAccountAndDebitCardAndSeniorAccount_whenAgeMoreThan64AndAndIncomeBetween1And12k() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(67)
            .income(10)
            .build());

      assertThat(result).isEqualTo(List.of(CURRENT_ACCOUNT, SENIOR_ACCOUNT, DEBIT_CARD));
   }

   @Test
   void should_return_currentAccountAndCreditCardAndSeniorAccount_whenAgeMoreThan64AndAndIncomeBetween1And12k() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(67)
            .income(30000)
            .build());

      assertThat(result).isEqualTo(List.of(CURRENT_ACCOUNT, SENIOR_ACCOUNT, CREDIT_CARD));
   }

   @Test
   void should_return_currentAccountAndCurrentAccountPlusAndSeniorAccountAndCreditCardAndGoldenCreditCard_whenAgeMoreThan64AndAndIncomeMoreThan40k() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(67)
            .income(50000)
            .build());

      assertThat(result).isEqualTo(List.of(CURRENT_ACCOUNT, CURRENT_ACCOUNT_PLUS, SENIOR_ACCOUNT, CREDIT_CARD, GOLD_CREDIT_CARD));
   }

   @ParameterizedTest
   @ValueSource(ints = {18, 65})
   void should_return_studentAccount(int age) {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(age)
            .income(50000)
            .student(true)
            .build());

      assertThat(result).contains(STUDENT_ACCOUNT);
   }

   @Test
   void should_notReturnStudentAccount() {
      var result = productFinder.getProducts(CustomerInfoDto.builder()
            .age(17)
            .income(50000)
            .student(true)
            .build());

      assertThat(result).doesNotContain(STUDENT_ACCOUNT);
   }
}