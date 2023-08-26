package seb.homework.productselector.product.create;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collections;

public class CoreTestConfiguration {

   private final String dump;
   private final Logger logger = LoggerFactory.getLogger(CoreTestConfiguration.class);

   public CoreTestConfiguration(String dump) {
      this.dump = dump;
   }

   @Bean("flyway")
   public Flyway flyway(DataSource dataSource) {
      ClassicConfiguration configuration = new ClassicConfiguration();
      configuration.setDataSource(dataSource);
      configuration.setSqlMigrationPrefix("");
      configuration.setBaselineOnMigrate(true);
      configuration.setBaselineVersionAsString("00000000000000");
      configuration.setBaselineDescription("Core Flyway initialized on existing schema");
      configuration.setOutOfOrder(false);
      configuration.setIgnoreMissingMigrations(true);
      configuration.setValidateOnMigrate(false);
      Flyway flyway = new Flyway(configuration);
      flyway.migrate();
      return flyway;
   }

   @Bean
   public MySQLContainer<?> mySQLContainer() throws IOException, InterruptedException {
      MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7")
            .withTmpFs(Collections.singletonMap("/var/lib/mysql", "rw"))
            .withDatabaseName("producttestdb")
            .withUsername("testuser")
            .withPassword("test")
            .withClasspathResourceMapping(dump, "/dump.sql", BindMode.READ_WRITE)
            .withClasspathResourceMapping("/my-custom.cnf", "/etc/mysql/conf.d/my-custom.cnf", BindMode.READ_WRITE)
            .withLogConsumer(new Slf4jLogConsumer(logger));
      mySQLContainer.start();
      Container.ExecResult result = mySQLContainer.execInContainer("sh", "-c", "mysql -u testuser -ptest producttestdb < dump.sql");
      logger.debug(result.getStdout());
      if (result.getStderr() != null) {
         logger.error(result.getStderr());
      }

      return mySQLContainer;
   }

   @Bean
   @DependsOn("mySQLContainer")
   public DataSource dataSource(MySQLContainer<?> mySQLContainer) {
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setDriverClassName(mySQLContainer.getDriverClassName());
      ds.setUrl(mySQLContainer.getJdbcUrl() + "?autoReconnect=true&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&serverTimezone=UTC&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true");
      ds.setUsername(mySQLContainer.getUsername());
      ds.setPassword(mySQLContainer.getPassword());
      return ds;
   }

   @Bean
   public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
      return new NamedParameterJdbcTemplate(dataSource);
   }

   @Bean
   public DataSourceTransactionManager transactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
   }
}
