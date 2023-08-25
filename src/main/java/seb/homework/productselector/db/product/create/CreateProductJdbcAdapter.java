package seb.homework.productselector.db.product.create;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import seb.homework.productselector.domain.product.create.CreateProductDto;
import seb.homework.productselector.domain.product.create.CreateProductPort;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CreateProductJdbcAdapter implements CreateProductPort {

   private final NamedParameterJdbcTemplate jdbc;

   private String query;
   Map<String, Object> params;

   @Override
   public void create(CreateProductDto product) {
      populateQueryAndParameters(product);
      GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
      jdbc.update(query, new MapSqlParameterSource(params), generatedKeyHolder);
   }

   private void populateQueryAndParameters(CreateProductDto product) {
      params = new HashMap<>();
      StringBuilder valuesPart = new StringBuilder(" VALUES (:name");

      StringBuilder insertPart = new StringBuilder("INSERT INTO product(name");
      params.put("name", product.getName());
      if (product.getMinAge() != null) {
         params.put("min_age", product.getMinAge());
         insertPart.append(", min_age");
         valuesPart.append(", :min_age");
      }
      if (product.getMaxAge() != null) {
         params.put("max_age", product.getMaxAge());
         insertPart.append(", max_age");
         valuesPart.append(", :max_age");
      }
      if (product.getMinIncome() != null) {
         params.put("min_income", product.getMinIncome());
         insertPart.append(", min_income");
         valuesPart.append(", :min_income");
      }
      if (product.getMaxIncome() != null) {
         params.put("max_income", product.getMaxIncome());
         insertPart.append(", max_income");
         valuesPart.append(", :max_income");
      }
      if (product.getMustBeStudent() != null) {
         params.put("must_be_student", product.getMustBeStudent());
         insertPart.append(", must_be_student");
         valuesPart.append(", :must_be_student");
      }

      insertPart.append(" ) ");
      valuesPart.append(" );");

      query = insertPart.append(valuesPart).toString();
   }
}
