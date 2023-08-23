package seb.homework.productselector.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seb.homework.productselector.core.product.CustomerInfoDto;
import seb.homework.productselector.core.product.ProductSuitabilityUseCase;

@Entity
public class Product  {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Column(name = "id", nullable = false)
   private Long id;

   String name;
   Integer minAge;
   Integer maxAge;
   Integer minIncome;
   Integer maxIncome;
   Boolean mustBeStudent;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getMinAge() {
      return minAge;
   }

   public void setMinAge(Integer minAge) {
      this.minAge = minAge;
   }

   public Integer getMaxAge() {
      return maxAge;
   }

   public void setMaxAge(Integer maxAge) {
      this.maxAge = maxAge;
   }

   public Integer getMinIncome() {
      return minIncome;
   }

   public void setMinIncome(Integer minIncome) {
      this.minIncome = minIncome;
   }

   public Integer getMaxIncome() {
      return maxIncome;
   }

   public void setMaxIncome(Integer maxIncome) {
      this.maxIncome = maxIncome;
   }

   public Boolean getMustBeStudent() {
      return mustBeStudent;
   }

   public void setMustBeStudent(Boolean mustBeStudent) {
      this.mustBeStudent = mustBeStudent;
   }
}
