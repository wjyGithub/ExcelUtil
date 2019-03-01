package export;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * 单元格处理器
 * @author jianyuan.wei@hand-china.com
 * @date 2019/3/1 10:09
 */
public class CellHandler {


    /**
     * 设置字符串数据到单元格
     * @param cell
     * @param value
     */
    public static void setStringToCell(Cell cell,String value) {
        if(StringUtils.isEmpty(value)) {
            value = "";
        }
        cell.setCellValue(value);
    }
    /**
     *  创建单元格
     * @param row
     * @param columnNum 某一行的第几列单元格
     * @return
     */
    public static Cell createCell(Row row,Integer columnNum) {
        Cell cell = row.getCell(columnNum);
        cell = cell == null ? row.createCell(columnNum) : cell;
        return cell;
    }

}
