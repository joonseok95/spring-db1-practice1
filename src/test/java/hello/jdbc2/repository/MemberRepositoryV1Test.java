package hello.jdbc2.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc2.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc2.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;
    @BeforeEach
    void BeforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException {

        //save
        Member member1 = new Member("member521", 10000);
        repository.save(member1);

        //findById
        Member findMember = repository.findById(member1.getMemberId());
        log.info("findMember = {}", findMember);
        assertThat(member1).isEqualTo(findMember);

        //update : money 10000 -> 20000
        repository.update(member1.getMemberId(), 20000);
        Member findMember2 = repository.findById(member1.getMemberId());
        assertThat(findMember2.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member1.getMemberId());
        assertThatThrownBy(() -> repository.findById(member1.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

    }
}