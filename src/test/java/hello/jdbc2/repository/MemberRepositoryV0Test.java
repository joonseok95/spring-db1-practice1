//package hello.jdbc2.repository;
//
//import hello.jdbc2.domain.Member;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.util.NoSuchElementException;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//class MemberRepositoryV0Test {
//
//    MemberRepositoryV0 repository = new MemberRepositoryV0();
//
//    @Test
//    void crud() throws SQLException {
//
//        //save
//        Member member1 = new Member("member521", 10000);
//        repository.save(member1);
//
//        //findById
//        Member findMember = repository.findById(member1.getMemberId());
//        log.info("findMember = {}", findMember);
//        assertThat(member1).isEqualTo(findMember);
//
//        //update : money 10000 -> 20000
//        repository.update(member1.getMemberId(), 20000);
//        Member findMember2 = repository.findById(member1.getMemberId());
//        assertThat(findMember2.getMoney()).isEqualTo(20000);
//
//        //delete
//        repository.delete(member1.getMemberId());
//        assertThatThrownBy(() -> repository.findById(member1.getMemberId()))
//                .isInstanceOf(NoSuchElementException.class);
//
//    }
//}