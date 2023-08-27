package seb.homework.productselector.validation;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class ValidationUtils {

   public static <T> T notNull(T object, String propertyName, String message) {
      if (object == null) {
         throw new DomainException(propertyName, message);
      }
      return object;
   }

   public static void notEmpty(String stringValue, String propertyName, String message) {
      if (StringUtils.isEmpty(stringValue)) {
         throw new DomainException(propertyName, message);
      }
   }

   public static void assertNotNegativeInteger(Integer integer, String propertyName, String message) throws DomainException {
      if (integer != null && integer < 0) {
         throw new DomainException(propertyName, message);
      }
   }

   public static void assertBiggerThan(Integer bigger, Integer smaller, String propertyNameBigger, String propertyNameSmaller) throws DomainException {
      if (bigger != null && smaller != null && smaller > bigger) {
         throw new DomainException(propertyNameBigger + ", " + propertyNameSmaller, propertyNameBigger + " must be bigger then " + propertyNameSmaller);
      }
   }


}
