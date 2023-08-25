package seb.homework.productselector.domain.validation;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
   private final String propertyName;

   public DomainException(String message, String propertyName) {
      super(message);
      this.propertyName = propertyName;
   }
}
