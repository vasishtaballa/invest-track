package dev.vasishta.invest.track.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
@Data
public class DBConfig {
    private HikariDataSource dataSource;

    @Value("${jdbcUrl}")
    private String jdbcUrl;

    @Value("${dataSource.user}")
    private String user;

    @Value("${dataSource.password}")
    private String password;

    @Value("${dataSource.cachePrepStmts}")
    private String cachePreparedStatements;

    @Value("${dataSource.prepStmtCacheSize}")
    private String preparedStatementCacheSize;

    @Value("${dataSource.prepStmtCacheSqlLimit}")
    private String preparedStatementCacheSqlLimit;

    @Value("${driverClassName}")
    private String driverClassName;

    @PostConstruct
    public void setDataSource() {
        Properties properties = new Properties();
        properties.setProperty("jdbcUrl", jdbcUrl);
        properties.setProperty("dataSource.user", user);
        properties.setProperty("dataSource.password", password);
        properties.setProperty("dataSource.cachePrepStmts", cachePreparedStatements);
        properties.setProperty("dataSource.prepStmtCacheSize", preparedStatementCacheSize);
        properties.setProperty("dataSource.prepStmtCacheSqlLimit", preparedStatementCacheSqlLimit);
        properties.setProperty("driverClassName", driverClassName);
        properties.setProperty("dataSource.driverClassName", driverClassName);
        HikariConfig hikariConfig = new HikariConfig(properties);
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setConnectionTimeout(300000);
        hikariConfig.setConnectionTimeout(120000);
        hikariConfig.setLeakDetectionThreshold(300000);
        dataSource = new HikariDataSource(hikariConfig);
    }
}
