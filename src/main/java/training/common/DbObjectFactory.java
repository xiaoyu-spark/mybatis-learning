package training.common;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by admin on 2018/4/11.
 */
public class DbObjectFactory extends DefaultObjectFactory {
    public Object create(Class type) {
        return super.create(type);
    }
//    public Object create(Class type, List<Class> constructorArgTypes, List<Object> constructorArgs) {
//        return super.create(type, constructorArgTypes, constructorArgs);
//    }
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
