package seb.homework.productselector.product.create;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seb.homework.productselector.validation.DomainException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class ProductCreatorTest {

   private static final String TEST_PRODUCT = "test product";
   private static final String EMPTY_STRING = "";
   @Mock
   private CreateProductPort createProductPort;

   @InjectMocks
   private ProductCreator productCreator;


   @Test
   void should_create() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .build();

      productCreator.create(createProductDto);

      verify(createProductPort, times(1)).create(createProductDto);
   }

   @Test
   void should_throw_when_ProductNull() {
      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(null));
      assertThat(exception.getPropertyName()).isEqualTo("product");
      assertThat(exception.getMessage()).isEqualTo("Product cannot be null");

      verifyNoInteractions(createProductPort);
      verifyNoInteractions(createProductPort);
   }

   @Test
   void should_throw_whenProductName_Null() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("productName");
      assertThat(exception.getMessage()).isEqualTo("Product name cannot be empty or null");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void should_throw_when_ProductNameEmpty() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(EMPTY_STRING)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("productName");
      assertThat(exception.getMessage()).isEqualTo("Product name cannot be empty or null");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void shouldThrow_when_minimumAgeNegative() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .minAge(-5)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("product.minAge");
      assertThat(exception.getMessage()).isEqualTo("Product minimum age cannot be negative");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void shouldThrow_when_maximumAgeNegative() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .maxAge(-5)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("product.maxAge");
      assertThat(exception.getMessage()).isEqualTo("Product maximum age cannot be negative");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void shouldThrow_when_minimumAgeBiggerThanMaximumAge() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .minAge(35)
            .maxAge(19)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("product.maxAge" + ", " + "product.minAge");
      assertThat(exception.getMessage()).isEqualTo("product.maxAge must be bigger then product.minAge");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void shouldThrow_when_minimumIncomeNegative() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .minIncome(-100)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("product.minIncome");
      assertThat(exception.getMessage()).isEqualTo("Product minimum income cannot be negative");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void shouldThrow_when_maximumIncomeNegative() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .maxIncome(-100)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("product.maxIncome");
      assertThat(exception.getMessage()).isEqualTo("Product maximum income cannot be negative");

      verifyNoInteractions(createProductPort);
   }

   @Test
   void shouldThrow_when_minimumAIncomeBiggerThanMaximumIncome() {
      CreateProductDto createProductDto = CreateProductDto.builder()
            .name(TEST_PRODUCT)
            .minIncome(30000)
            .maxIncome(20000)
            .build();

      DomainException exception = assertThrows(DomainException.class, () -> productCreator.create(createProductDto));
      assertThat(exception.getPropertyName()).isEqualTo("product.maxIncome" + ", " + "product.minIncome");
      assertThat(exception.getMessage()).isEqualTo("product.maxIncome must be bigger then product.minIncome");

      verifyNoInteractions(createProductPort);
   }




}