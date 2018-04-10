package training.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2018/4/10.
 */
public class SessionFactoryUtil {
    private static final SqlSessionFactory SESSION_FACTORY ;
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);

    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return SESSION_FACTORY;
    }

    public static SqlSession getSqlSession() {
        return SESSION_FACTORY.openSession();
    }
}
