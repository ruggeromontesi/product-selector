package seb.homework.productselector.product.find;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductFinderTest {

   private static final String TEST_PRODUCT_1 = "test product 1";
   private static final String TEST_PRODUCT_2 = "test product 2";
   private static final List<FindProductDto> PRODUCT_LIST = List.of(
         FindProductDto.builder().name(TEST_PRODUCT_1).build(),
         FindProductDto.builder().name(TEST_PRODUCT_2).build()
   );
   @Mock
   private FindProductPort findProductPort;

   @InjectMocks
   private ProductFinder productFinder;

   @Test
   void shouldFindAll() {
      when(findProductPort.findAll()).thenReturn(PRODUCT_LIST);

      var result = productFinder.findAll();

      assertThat(result).isEqualTo(PRODUCT_LIST);
   }

}