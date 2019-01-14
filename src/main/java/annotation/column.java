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
public @interface column {

    /**
     * 指定该字段和excel中第几列映射
     */
    int index();


}
