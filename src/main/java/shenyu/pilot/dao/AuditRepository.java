package shenyu.pilot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import shenyu.pilot.dao.util.IdGenerator;
import shenyu.pilot.model.AuditObject;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by sheyu on 5/16/2017.
 */
@Repository
public class AuditRepository {

    private JdbcOperations jdbcOperations;


    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcOperations = new JdbcTemplate(dataSource);
    }

    public AuditObject save(AuditObject auditObject) {
        auditObject.setId(idGenerator.next());
        jdbcOperations.update("insert into T_AUDIT_LOGS (id, who, action, action_time, target, target_type, target_id) values (?, ?, ?, ?, ?, ?, ?)",
                auditObject.getId(),
                auditObject.getWho(),
                auditObject.getAction(),
                auditObject.getActionTime(),
                auditObject.getTarget(),
                auditObject.getTargetType(),
                auditObject.getTargetId());
        return auditObject;
    }

    public AuditObject getById(String id) {
        return jdbcOperations.queryForObject("select * from T_AUDIT_LOGS where id = ?",auditObjectRowMapper(), id);
    }

    private RowMapper<AuditObject> auditObjectRowMapper() {
        return (ResultSet rs, int i) -> {
            AuditObject auditObject = new AuditObject(
                    rs.getString("who"),
                    rs.getString("action"),
                    rs.getLong("action_time"),
                    rs.getString("target"),
                    rs.getString("target_type"),
                    rs.getString("target_id")
            );
            auditObject.setId(rs.getString("id"));
            return auditObject;
        };
    }

    public List<AuditObject> getByTargetTypeAndTargetId(String type, String id) {
        return jdbcOperations.query("select * from T_AUDIT_LOGS where target_type = ? and target_id = ?", auditObjectRowMapper(), type, id);
    }
}
