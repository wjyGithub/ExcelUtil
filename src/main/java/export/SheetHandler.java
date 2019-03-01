package export;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import support.ModelExecutor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sheet页处理器
 * @author jianyuan.wei@hand-china.com
 * @date 2019/3/1 10:07
 */
public class SheetHandler<T> implements Cloneable {

    /**
     * 需要处理的sheet页
     */
    private Sheet sheet;

    /**
     * 需要写入excel的数据流
     */
    private List<T> datas;
    /**
     * 实体类
     */
    private Class<T> clazz;

    /**
     * 实体类的描述信息
     */
    private Map<Integer, Field> modelDescMap;

    public SheetHandler(Sheet sheet,List<T> datas,Class<T> clazz) {
        this.sheet = sheet;
        this.datas = datas;
        this.clazz = clazz;
        this.modelDescMap = ModelExecutor.obtainModelDesc(clazz);
        initSheet();
    }

    /**
     * 用于初始化excel的样式
     */
    private void initSheet(){
        setColumnAutoSize();
    }

    /**
     * 设置单元格宽度自适应(单元格下标默认从0开始)
     */
    private void setColumnAutoSize() {
        TreeMap<Integer,Field> treeMap = new TreeMap<Integer, Field>(modelDescMap);
        for(int i = 0; i<treeMap.lastKey(); i++) {
            //设置第5列为宽度自适应(默认列数从0开始),需要下面两步
            //有些自适应不是特别理想
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)*16/10);
        }
    }

    /**
     * 用于将数据写入到excel中
     * @param startRow 从excel中第几行开始写入数据
     */
    public void handler(int startRow) {
        int rowNum = startRow;
        for(T data : datas) {
            handlerRow(data,rowNum);
            rowNum++;
        }
    }

    /**
     * 向excel中具体某一行写入数据
     * @param rowNum excel中对应的行号
     */
    private void handlerRow(T data,int rowNum) {
        //获取sheet页中指定的行
        Row row = sheet.getRow(rowNum);
        if(row == null) {
            row = sheet.createRow(rowNum);
        }
        RowHandler<T> rowHandler = new RowHandler(modelDescMap,row,false);
        rowHandler.rowBuild(data,rowNum);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
