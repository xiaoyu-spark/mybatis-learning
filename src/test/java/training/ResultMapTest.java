package training;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import training.bean.Role;
import training.bean.User;
import training.utils.SingleSessionFactoryUtil;

import java.util.List;

public class ResultMapTest {
    @Test
    public void selectUserById() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.userMapper.selectUserById";
        User user = session.selectOne(method, 1);
        System.out.println(user);
        System.out.println(user.getSetting());
        System.out.println(user.getRoles()==null?"":user.getRoles().get(0));
    }
}
