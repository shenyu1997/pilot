package shenyu.pilot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shenyu.pilot.model.Tenant;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Repository
public class TenantRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcOperations = new JdbcTemplate(dataSource);
    }

    public List<Tenant> getAll() {
        return jdbcOperations.query("select * from T_TENANTS", tenantRowMapper());
    }

    private RowMapper<Tenant> tenantRowMapper() {
        return (ResultSet rs, int number) -> {
            Tenant tenant = new Tenant();
            tenant.setId(rs.getString("id"));
            tenant.setName(rs.getString("name"));
            tenant.setState(rs.getString("state"));
            return tenant;
        };
    }

}
