package shenyu.pilot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shenyu.pilot.common.Audit;
import shenyu.pilot.dao.util.IdGenerator;
import shenyu.pilot.model.Tenant;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Repository
public class TenantRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    private IdGenerator idGenerator;

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

    public Tenant getById(String id) {
        return jdbcOperations.queryForObject("select * from T_TENANTS where id=?",new Object[]{id},tenantRowMapper());
    }

    @Audit
    public Tenant save(Tenant tenant) {
        tenant.setId(idGenerator.next());
        jdbcOperations.update("insert into T_TENANTS (id, name, state) values (?, ?, ?)",
                new Object[]{tenant.getId(),tenant.getName(), tenant.getState()});
        return tenant;
    }

    @Audit
    public void update(Tenant tenant) {
        jdbcOperations.update("update T_TENANTS set state=? where id=?", new Object[]{tenant.getState(), tenant.getId()});
    }

}
