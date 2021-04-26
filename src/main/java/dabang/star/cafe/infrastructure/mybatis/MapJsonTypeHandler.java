package dabang.star.cafe.infrastructure.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dabang.star.cafe.application.exception.ParseException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MapJsonTypeHandler extends BaseTypeHandler<Map<String, Integer>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Integer> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, mapToJson(parameter));
    }

    @Override
    public Map<String, Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return jsonToMap(rs.getString(columnName));
    }

    @Override
    public Map<String, Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return jsonToMap(rs.getString(columnIndex));
    }

    @Override
    public Map<String, Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return jsonToMap(cs.getString(columnIndex));
    }

    private String mapToJson(Map<String, Integer> object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }

    private Map<String, Integer> jsonToMap(String content) {
        if (content != null && !content.isEmpty()) {
            try {
                return mapper.readValue(content, HashMap.class);
            } catch (JsonProcessingException e) {
                throw new ParseException(e);
            }
        } else {
            return null;
        }
    }

}
