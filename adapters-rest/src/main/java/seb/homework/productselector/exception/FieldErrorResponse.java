package seb.homework.productselector.exception;

import lombok.Getter;

@Getter
public class FieldErrorResponse {
   private final String fieldName;
   private final String errorMessage;

   public FieldErrorResponse(String fieldName, String errorMessage) {
      super();
      this.fieldName = fieldName;
      this.errorMessage = errorMessage;
   }

}
