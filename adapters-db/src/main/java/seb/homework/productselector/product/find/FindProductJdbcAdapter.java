package seb.homework.productselector.product.find;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FindProductJdbcAdapter implements FindProductPort {
   private final NamedParameterJdbcTemplate jdbc;

   public List<FindProductDto> findAll() {
      String sql = "SELECT * FROM PRODUCT ";
      return jdbc.query(sql, Map.of(), FindProductJdbcAdapter::mapRow);
   }

   private static FindProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
      return FindProductDto.builder()
            .name(rs.getString("name"))
            .minAge(rs.getObject("min_age", Integer.class))
            .maxAge(rs.getObject("max_age", Integer.class))
            .minIncome(rs.getObject("min_income", Integer.class))
            .maxIncome(rs.getObject("max_income", Integer.class))
            .mustBeStudent(rs.getBoolean("must_be_student"))
            .build();
   }


}
