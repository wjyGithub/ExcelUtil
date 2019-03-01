package util;


/**
 * @author jianyuan.wei@hand-china.com
 * @date 2019/2/26 16:24
 */
public class ClassLoaderUtil {

    /**
     * 获取classLoader
     *
     * 获取资源的相对路径统一是以classes/为当前路径进行检索的
     * getResource/getResourceAsStream/getResource
     * @return
     */
    public static ClassLoader getDefaultClassLoader(){

        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        }catch(Throwable e) {}
        if(classLoader == null) {
            classLoader = ClassLoaderUtil.class.getClassLoader();
            if(classLoader == null) {
                try {
                    classLoader = classLoader.getSystemClassLoader();
                }catch(Throwable e){}
            }
        }
        return classLoader;
    }

}
