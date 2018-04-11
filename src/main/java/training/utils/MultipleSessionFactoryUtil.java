package training.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import training.constant.SystemConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/4/11.
 */
public class MultipleSessionFactoryUtil {
    private static final Map<String, SqlSessionFactory> MULTIPLE_SESSION_FACTORY = new HashMap<String, SqlSessionFactory>();

    public static SqlSessionFactory getSqlSessionFactory(String mybatisConfig, String environment) throws IOException {
        if(StringUtils.isEmpty(mybatisConfig)) {
            mybatisConfig = SystemConstant.MYBATIS_CONFIG_PATH;
        }
        if(!MULTIPLE_SESSION_FACTORY.containsKey(environment)) {
            synchronized (MultipleSessionFactoryUtil.class) {
                if(!MULTIPLE_SESSION_FACTORY.containsKey(environment)) {
                    setSqlSessionFactory(mybatisConfig, environment);
                }
            }
        }
        return MULTIPLE_SESSION_FACTORY.get(environment);
    }

    private static void setSqlSessionFactory(String mybatisConfig, String environment) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(mybatisConfig);
        } catch (IOException e) {
            throw e;
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        if(null == sessionFactory) {
            throw new NullPointerException("this environment not exist");
        }
        MULTIPLE_SESSION_FACTORY.put(environment, sessionFactory);
    }
}
