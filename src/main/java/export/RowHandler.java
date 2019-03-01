package export;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * 行处理器
 * @author jianyuan.wei@hand-china.com
 * @date 2019/3/1 10:09
 */
public class RowHandler<T> implements Cloneable {

    /**
     * 类的描述信息
     */
    private Map<Integer,Field> clazzDesMap;

    private Row row;

    /**
     * 是否excel导入需要序号列
     * 对于一些情况,可能前端含有序号列,但是序号列非后端传递过去的，
     * 是前端自动生成的，所以导出时,就需要自动将序号补上
     */
    private boolean requiredNum;

    public RowHandler(Map<Integer,Field> clazzDesMap,Row row,boolean requiredNum) {
        this.clazzDesMap = clazzDesMap;
        this.row = row;
        this.requiredNum = requiredNum;
    }

    /**
     * 用于构建带有数据的一行
     * @param obj
     * @param rowNum  序号列
     */
    public void rowBuild(T obj, int rowNum) {
        TreeMap<Integer,Field> treeMap = new TreeMap<Integer, Field>(clazzDesMap);
        for(int i=0; i< treeMap.lastKey(); i++) {
            try{
                Cell cell = CellHandler.createCell(row,i);
                Field field = treeMap.get(i);
                Object value = "";
                if(field != null) {
                    value = field.get(obj) == null ? "" : field.get(obj);
                }else if(requiredNum) {
                    value = rowNum;
                }
                CellHandler.setStringToCell(cell,String.valueOf(value));
            }catch(IllegalAccessException e) {}
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
