package training;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import training.bean.Role;
import training.bean.Setting;
import training.utils.SingleSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/11.
 */
public class InsertTest {
    @Test
    public void insert() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.insertRole";
        Role role = new Role();
        role.setEnName("admin");
        role.setName("管理员");
        role.setDescription("test");
        session.insert(method, role);
        session.commit();
        System.out.println(role.getId());
        System.out.println(role.getCreatedAt());
    }
    @Test
    public void insertRoleBatch() {
        SqlSession session = SingleSessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.roleMapper.insertRoleBatch";
        List<Role> roleList = new ArrayList<>();
        for(int i=0;i<5;i++) {
            Role role = new Role();
            role.setEnName("admin" + i);
            role.setName("管理员" + i);
            role.setDescription("test");
            roleList.add(role);
        }
        session.insert(method, roleList);
        session.commit();
        for (Role role : roleList){
            System.out.println(role.getCreatedAt());
        }
    }
}
