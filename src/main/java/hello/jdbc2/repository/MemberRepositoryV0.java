package hello.jdbc2.repository;

import hello.jdbc2.connection.DBConnectionUtil;
import hello.jdbc2.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, member.getMemberId());
            ps.setInt(2, member.getMoney());
            ps.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.info("db error", e);
            throw e;
        } finally {
            close(con, ps, null);
        }


    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, memberId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }
            else {
                throw new NoSuchElementException("member not found member_id = "+ memberId);
            }
        } catch (SQLException e) {
            log.info("db error", e);
            throw e;
        } finally {
            close(con, ps, rs);
        }

    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money = ? where member_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, money);
            ps.setString(2, memberId);
            int resultSize = ps.executeUpdate();
            log.info("resultSize = {}", resultSize);
        } catch (SQLException e) {
            log.info("db error", e);
            throw e;
        } finally {
            close(con, ps, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, memberId);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.info("db error", e);
            throw e;
        } finally {
            close(con, ps, null);
        }
    }


    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("rs error", e);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("stmt error", e);
            }

        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("con error", e);
            }
        }

    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

}
