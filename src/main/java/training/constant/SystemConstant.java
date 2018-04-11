package training.constant;

import training.utils.SystemEnvUtils;

/**
 * Created by admin on 2018/4/11.
 */
public class SystemConstant {
    public static final String MYBATIS_CONFIG_PATH;

    static {
        MYBATIS_CONFIG_PATH = SystemEnvUtils.getSystemEnv("MYBATIS_CONFIG_PATH", "mybatis-config.xml");
    }
}
