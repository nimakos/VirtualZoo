package gr.nikolis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {

    @Value("${spring.datasource.applicationName:postgresql}")
    private String datasourceApplicationName;
    @Value("${spring.datasource.username:root}")
    private String datasourceUserName;
    @Value("${spring.datasource.password:123}")
    private String dataSourcePassword;
    @Value("${spring.datasource.address1:localhost}")
    private String datasourceAddress;
    @Value("${spring.datasource.address2:sql-container-yml}")
    private String datasourceAddress3;
    @Value("${spring.datasource.dbname:postgres}")
    private String datasourceDBName;

    @Bean
    public String getDatasourceApplicationName() {
        return datasourceApplicationName;
    }

    @Bean
    public String getDatasourceUserName() {
        return datasourceUserName;
    }

    @Bean
    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    /**
     * Run SQL localhost
     * When we don't use any agile methodology such as Jenkins or Docker
     *
     * @return localhost sql address
     */
    @Bean
    public String getDatasourceAddress() {
        return datasourceAddress;
    }

    /**
     * Run sql from docker linked along with the application
     * <p>
     * We use Docker along with Sql both linked in container (Using docker-compose.yml)
     * SOS :
     * first use  : mvn -Dmaven.test.skip=true clean install
     * second use : docker-compose up
     * rebuild use: docker-compose up --build
     *
     * @return container sql address
     */
    @Bean
    public String getDatasourceAddress2() {
        return datasourceAddress3;
    }

    @Bean
    public String getDatasourceDBName() {
        return datasourceDBName;
    }

    /**
     * Sql Datasource
     * <p>
     *
     * @return The DataSource object
     */
    @Bean(name = "mySqlDataSource")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:" + getDatasourceApplicationName() + "://" + getDatasourceAddress() + "/" + getDatasourceDBName() + "?" +
                        "createDatabaseIfNotExist=true&" +
                        "serverTimezone=UTC&" +
                        "useUnicode=true&" +
                        "characterEncoding=utf8&" +
                        "allowPublicKeyRetrieval=true")
                .username(getDatasourceUserName())
                .password(getDataSourcePassword())
                .build();
    }
}
