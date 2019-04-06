# ExcelUtil
对POI框架的封装，用于快速开发Excel导入导出

说明
```text
导出（导入时，type为无用字段）:
@Column(index=0,type=String.class): 表明该字段的数据需要放在excel表中的第0列
导入
@Column(index=0,type=String.class): 表明excel中的第index列需要被映射到该字段上,type用于告诉导入工具,
                                    将excel中该列的数据转为指定的数据类型
```

===============================================================================
导入设计思想:  
![excel导入设计思想图](https://github.com/wjyGithub/ExcelUtil/blob/master/src/main/resources/images/excel%E5%AF%BC%E5%85%A5%E8%AE%BE%E8%AE%A1%E6%80%9D%E6%83%B3.png)  
如图所示,该EXCEL导入工具的主要设计思想是,将业务的处理逻辑和excel的导入操作分离开来,抽离出一个方便使用的
excel导入工具,使开发人员可以无需关注excel的导入细节,专注业务逻辑的处理

excel导入
```text
1. 在实体类的字段上,添加该注解
public class ImportDemo {
    
    @Column(index=0,type=String.class)
    private String name;
    
    @Column(index=1,type=Date.class)
    private Date birthDate;
    
    @Column(index=2,type=Long.class)
    private Long age;
    
    ...
}

2. 转为实体类
public void import(MultipartFile file) {
    List<ImportDemo> importDemos = ImportHelper.toPojo(file,ImportDemo.class);
}
```
===========================================================================

导出设计思想:  
![excel导出设计思想图](https://github.com/wjyGithub/ExcelUtil/blob/master/src/main/resources/images/excel%E5%AF%BC%E5%87%BA%E8%AE%BE%E8%AE%A1%E6%80%9D%E6%83%B3.png)  
如图所示,该EXCEL导出工具的主要设计思想是,将数据流和文件流之间的映射进行解耦,开发者只需关注数据流的获取,
而无需了解数据和文件流之间的转换关系,方便用户的开发
的映射

excel导出
```$xslt

用法:
1. 在resources/template目录下，创建一个导出模板
导出模板只需要包含字段头部描述信息即可
2. 在实体类字段添加注解
public class ExportDemo {

    @Column(index=0)
    private String name;

}

3. 获取数据源
List<ExportDemo> listDemo = ....;
4. 数据源和文件流进行绑定
/**
* arg1: 模板名
* arg2: 数据源
* arg3: 实体类class
*/
Workbook workbook = ExportHelper.workbookBuild("ExportDemoTemplate.xlsx", listDemo, ExportDemo.class);
5. 写入响应流中，并返回给前端
ResponseUtil.writeToResponse(response,workbook,"demo.xlsx");
```


