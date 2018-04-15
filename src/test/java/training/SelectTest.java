package training;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import training.bean.Role;
import training.utils.SingleSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/11.
 */
public class SelectTest {
    @Test
    public void selectOne() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.getRole";
        Role role = session.selectOne(method, "1");
        System.out.println(role);
    }
    @Test
    public void getRolesOnSqlTag() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.getRolesOnSqlTag";
        List<Role> roleList = session.selectList(method);
        for (Role role : roleList) {
            System.out.println(role.toString() );
        }
    }

    @Test
    public void getRolesOnParameterTag() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.getRolesOnParameterTag";
        List<Role> roleList = session.selectList(method, 50);
        for (Role role : roleList) {
            System.out.println(role.toString() );
        }
    }

    @Test
    public void getRolesOnResultMapTag() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.getRolesOnResultMapTag";
        List<Role> roleList = session.selectList(method, 50);
        for (Role role : roleList) {
            System.out.println(role.toString() );
        }
    }
}
