package training;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import training.bean.Role;
import training.utils.SessionFactoryUtil;

/**
 * Created by admin on 2018/4/10.
 */
public class SesssionFactoryTest {
    @Test
    public void getSqlSessionFactory() {
        SqlSessionFactory sessionFactory = SessionFactoryUtil.getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        String method = "training.mapper.xml.roleMapper.getRole";
        Role role = session.selectOne(method, "1");
        System.out.println(role.getName());
    }
    @Test
    public void selectOne() {
        SqlSession session = SessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.getRole";
        Role role = session.selectOne(method, "1");
        System.out.println(role);
    }
}
