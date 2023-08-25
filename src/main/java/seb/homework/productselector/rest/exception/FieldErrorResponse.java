package seb.homework.productselector.rest.exception;

import lombok.Getter;

@Getter
public class FieldErrorResponse {
   private String fieldName;
   public FieldErrorResponse(String fieldName, String errorMessage) {
      super();
      this.fieldName = fieldName;
      this.errorMessage = errorMessage;
   }
   private String errorMessage;
}
