package imports;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import support.ModelExecutor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 该工具类主要用于解析Sheet
 * Created by jianyuan.wei@hand-china.com
 * on 2019/4/3 23:05.
 */
public class SheetResolve {

    private SheetResolve() {}


    /**
     * sheet解析成List对象,每行一个对象
     *
     * @param start
     * @param end
     * @return
     */
    public static final <T> List<T> sheetHandle(Sheet sheet, int start, int end, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> results = new ArrayList<>();
        Map<Integer, Field> metaInfo = ModelExecutor.obtainModelDesc(clazz);
        for (int i = start; i <= end; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                T pojo = RowResolve.toPojo(row, metaInfo, clazz);
                results.add(pojo);
            }
        }
        return results;
    }


}
