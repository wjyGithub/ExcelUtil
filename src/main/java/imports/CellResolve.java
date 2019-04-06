package imports;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 列的解析操作 做些格式的转换之类的
 * Created by jianyuan.wei@hand-china.com
 * on 2019/4/4 0:04.
 */
public class CellResolve {

    private static final Logger Log = LoggerFactory.getLogger(CellResolve.class);
    private CellResolve() {}


    /**
     * 对于Excel而言,如果该单元格是空，则Cell=null
     * 当获取单元格的内容抛异常时，则表示excel内部数据异常,该框架的处理方式是直接赋为null
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> T getValueFor(Cell cell, Class<T> clazz) {
        if (clazz == Date.class) {
            try {
                return clazz.cast(cell.getDateCellValue());
            }catch (Exception e) {
                //捕获的异常可能有两种,一: excel单元格为空，抛空指针异常; 二: 单元格内容格式出错
                Log.error("cell format error:",e.getMessage());
            }
        }

        if (clazz == String.class) {
            try {
                return clazz.cast(cell.getStringCellValue());
            }catch (Exception e) {
                Log.error("cell format error:",e.getMessage());
            }
        }

        if (clazz == Long.class) {
            try {
                return clazz.cast(((Double) cell.getNumericCellValue()).longValue());
            }catch (Exception e) {
                Log.error("cell format error:",e.getMessage());
            }
        }

        if (clazz == Integer.class) {
            try {
                return clazz.cast(((Double) cell.getNumericCellValue()).intValue());
            }catch (Exception e) {
                Log.error("cell format error:",e.getMessage());
            }
        }
        return null;
    }

}
