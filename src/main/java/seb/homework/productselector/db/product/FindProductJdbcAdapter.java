package seb.homework.productselector.db.product;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import seb.homework.productselector.core.product.Product;
import seb.homework.productselector.core.product.get.FindProductUseCase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FindProductJdbcAdapter implements FindProductUseCase {
   private final NamedParameterJdbcTemplate jdbc;

   public List<Product> findAll() {
      String sql = "SELECT * FROM PRODUCT ";
      return jdbc.query(sql, Map.of(), FindProductJdbcAdapter::mapRow);
   }

   private static Product mapRow(ResultSet rs, int rowNum) throws SQLException {
      return Product.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            //.minAge(rs.getInt("min_age"))
            .minAge(rs.getObject("min_age", Integer.class))
            .maxAge(rs.getObject("max_age", Integer.class))
            .minIncome(rs.getObject("min_income", Integer.class))
            .maxIncome(rs.getObject("max_income", Integer.class))
            .mustBeStudent(rs.getBoolean("must_be_student"))
            .build();
   }


}
