package shenyu.pilot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shenyu.pilot.aspect.Auditable;
import shenyu.pilot.dao.util.IdGenerator;
import shenyu.pilot.model.TenantOperation;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by sheyu on 5/12/2017.
 */
@Repository
public class TenantOperationRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    public void setDateSource(DataSource ds) {
        this.jdbcOperations = new JdbcTemplate(ds);
    };

    public TenantOperation save(TenantOperation operation) {
        operation.setId(idGenerator.next());
        jdbcOperations.update("insert into T_TENANT_OPERATIONS (id, tenant_id, action, action_time ,is_successful) values (?, ?, ?, ?, ?)",
                new Object[]{operation.getId(), operation.getTenantId(), operation.getAction(), new Date(), operation.getIsSuccessful()});
        return operation;
    }

    public TenantOperation getById(String operationId) {
        return jdbcOperations.queryForObject("select * from t_tenant_operations where id=?", tenantOperationRowMapper(), operationId);
    }

    private RowMapper<TenantOperation> tenantOperationRowMapper() {
        return (ResultSet rs, int number) -> {
            return new TenantOperation(
                    rs.getString("id"),
                    rs.getString("tenant_id"),
                    rs.getString("action"),
                    rs.getTimestamp("action_time").getTime(),
                    rs.getBoolean("is_successful")
            );
        };
    }
}
