package training;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import training.bean.Role;
import training.utils.MultipleSessionFactoryUtil;
import training.utils.SingleSessionFactoryUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2018/4/10.
 */
public class SesssionFactoryTest {
    @Test
    public void getSqlSessionFactory() {
        SqlSessionFactory sessionFactory = SingleSessionFactoryUtil.getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        String method = "training.mapper.xml.roleMapper.getRole";
        Role role = session.selectOne(method, "1");
        System.out.println(role.getName());
    }

    @Test
    public void getMultipleSessionFactory() throws IOException {
        SqlSessionFactory sessionFactory = MultipleSessionFactoryUtil.getSqlSessionFactory(null, "development2");

        SqlSession session = sessionFactory.openSession();
        String method = "training.mapper.xml.roleMapper.getRole";
        Role role = session.selectOne(method, "1");
        System.out.println(role.getName());
    }


}
