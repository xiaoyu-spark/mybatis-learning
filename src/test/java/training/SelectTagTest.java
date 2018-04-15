package training;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import training.bean.User;
import training.utils.SingleSessionFactoryUtil;

public class SelectTagTest {
    @Test
    public void selectWithWhereTag() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.userMapper.selectWithWhereTag";
        User paramUser = new User();
//        paramUser.setId("1");
//        paramUser.setName("admin");
        paramUser.setDescription("admin");
        User user = session.selectOne(method, paramUser);
        System.out.println(user);
        System.out.println(user.getSetting());
        System.out.println(user.getRoles()==null?"":user.getRoles().get(0));
    }
}
