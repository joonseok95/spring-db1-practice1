package hello.jdbc2.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc2.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("con1 = {}, class = {}", con1, con1.getClass());
        log.info("con2 = {}, class = {}", con2, con2.getClass());


    }

    @Test
    void dateSourceDriverManager() throws SQLException {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(driverManagerDataSource);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("con1 = {}, class = {}", con1, con1.getClass());
        log.info("con2 = {}, class = {}", con2, con2.getClass());


    }

}
