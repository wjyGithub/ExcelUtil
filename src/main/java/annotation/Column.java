package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识该字段在excel中存放于第几列
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 20:39
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    /**
     * 标记位于excel中的第几列
     */
    int index();

}
