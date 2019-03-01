# ExcelUtil
对POI框架的封装，用于快速开发Excel导入导出
```$xslt
该框架在导出过程中，主要的设计思想是将数据流和文件流进行解耦
使用户只需要关注数据流的获取,而无需了解底层文件流的操作以及之间
的映射
```
![ad](https://github.com/wjyGithub/ExcelUtil/blob/master/src/main/resources/images/excel%E5%AF%BC%E5%87%BA%E8%AE%BE%E8%AE%A1%E6%80%9D%E6%83%B3.png)


excel导出
```$xslt
说明:
@Column(index=0): 表明该字段的数据需要放在excel表中的第0列
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
