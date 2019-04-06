package support;

import annotation.Column;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * 进行数据的处理
 * @author jianyuan.wei@hand-china.com
 * @date 2019/3/1 10:34
 */
public class ModelExecutor {

    private ModelExecutor(){}

    /**
     * 获取实体类对的元信息
     * @return 列号和字段元信息映射  key:列号  value: 字段元信息
     */
    public static Map<Integer,Field> obtainModelDesc(Class<?> cls) {
        Map<Integer,Field> map = new TreeMap<>();
        if(cls == null) {
            throw new IllegalArgumentException("类名不能为空");
        }
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if(column != null) {
                int index = column.index();
                field.setAccessible(true);
                map.put(index,field);
            }
        }
        return map;
    }

}
