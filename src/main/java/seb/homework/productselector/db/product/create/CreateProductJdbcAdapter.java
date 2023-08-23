package seb.homework.productselector.db.product.create;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import seb.homework.productselector.core.product.create.CreateProductDto;
import seb.homework.productselector.core.product.create.CreateProductPort;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CreateProductJdbcAdapter implements CreateProductPort {

   private final NamedParameterJdbcTemplate jdbc;

   @Override
   public void create(CreateProductDto product) {
      String query = " INSERT INTO product(name, min_age, max_age, min_income, max_income, must_be_student) " +
            " VALUES (:name, :min_age, :max_age, :min_income, :max_income, :must_be_student )";

      Map<String, Object> params = Map.of(
            "name", product.getName(),
            "min_age", product.getMinAge(),
            "max_age", product.getMaxAge(),
            "min_income", product.getMinIncome(),
            "max_income", product.getMaxIncome(),
            "must_be_student", product.getMustBeStudent()
      );

      GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
      jdbc.update(query, new MapSqlParameterSource(params), generatedKeyHolder);
   }
}
