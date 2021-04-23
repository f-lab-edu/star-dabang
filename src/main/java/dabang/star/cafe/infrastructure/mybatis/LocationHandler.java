package dabang.star.cafe.infrastructure.mybatis;

import dabang.star.cafe.domain.office.Location;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Location.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class LocationHandler implements TypeHandler<Location> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Location parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public Location getResult(ResultSet rs, String columnName) throws SQLException {
        return Location.parseFrom(rs.getString(columnName));
    }

    @Override
    public Location getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Location.parseFrom(rs.getString(columnIndex));
    }

    @Override
    public Location getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Location.parseFrom(cs.getResultSet().getString(columnIndex));
    }
}
