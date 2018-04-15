package training.common;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by admin on 2018/4/11.
 */
@MappedJdbcTypes(JdbcType.INTEGER)
public class MyTypeHandler extends BaseTypeHandler<String> {
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("MyTypeHandler----setNonNullParameter");
        ps.setString(i, parameter);
    }

    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("MyTypeHandler----getNullableResult");
        return rs.getString(columnName);
    }

    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("MyTypeHandler----getNullableResult");
        return rs.getString(columnIndex);
    }

    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("MyTypeHandler----getNullableResult");
        return cs.getString(columnIndex);
    }
}
