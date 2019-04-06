package support;

import imports.SheetResolve;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianyuan.wei@hand-china.com
 * on 2019/4/4 0:40.
 */
public class ImportHelper {
    private static final Logger Log = LoggerFactory.getLogger(ImportHelper.class);

    private ImportHelper() {}

    /**
     * 将数据流转成Workbook对象
     *
     * @param file
     * @return
     */
    private static Workbook readExcel(MultipartFile file) {
        try (
                InputStream is = file.getInputStream();
                Workbook workbook = WorkbookFactory.create(is)
        ) {
            return workbook;
        } catch (IOException | InvalidFormatException e) {
            Log.error("", e.getStackTrace());
        }
        return null;
    }

    /**
     * 将文件流转成实体类对象
     * @param file
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> List<T> toPojo(MultipartFile file, Class<T> clazz) {
        Workbook workbook = readExcel(file);
        Sheet sheet = workbook.getSheetAt(0);
        int lastNum = sheet.getLastRowNum();
        try {
            List<T> objects = SheetResolve.sheetHandle(sheet, 1, lastNum, clazz);
            return objects;
        } catch (InstantiationException | IllegalAccessException e) {
            Log.error("error fail");
        }
        return new ArrayList<>();
    }
}
