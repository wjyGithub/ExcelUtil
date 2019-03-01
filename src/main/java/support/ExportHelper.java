package support;

import export.SheetHandler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.ClassLoaderUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 对于一些配置信息,如是否开启序列号,可做成配置文件
 * @author jianyuan.wei@hand-china.com
 * @date 2019/3/1 15:11
 */
public class ExportHelper {

    private static final String PATH_SUFFIX = "template/";

    /**
     * 生成workbook对象
     * @param templateName 导入模板名,如"xxx.xlsx"
     * @param datas 数据源
     * @param clazz 数据源的实体类
     * @param <T>
     * @return
     */
    public static <T> Workbook workbookBuild(String templateName,List<T> datas,Class<T> clazz) {
        ClassLoader classLoader = ClassLoaderUtil.getDefaultClassLoader();
        InputStream is = classLoader.getResourceAsStream(PATH_SUFFIX + templateName);
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            SheetHandler sheetHandler = new SheetHandler(sheet,datas,clazz);
            sheetHandler.handler(1);
            return workbook;
        } catch (IOException e) {}
        return null;
    }
}
