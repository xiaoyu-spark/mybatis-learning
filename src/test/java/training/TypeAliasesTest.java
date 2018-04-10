package training;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import training.bean.Role;
import training.bean.Setting;
import training.utils.SessionFactoryUtil;

/**
 * Created by admin on 2018/4/10.
 */
public class TypeAliasesTest {
    @Test
    public void selectOne() {
        SqlSession session = SessionFactoryUtil.getSqlSession();
        String method = "training.mapper.xml.settingMapper.getSetting";
        Setting setting = session.selectOne(method, "1");
        System.out.println(setting);
    }
}
