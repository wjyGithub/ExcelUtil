package util;

import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author jianyuan.wei@hand-china.com
 * @date 2019/3/1 16:57
 */
public class ResponseUtil {

    /**
     * 将文件数据写入到响应流中,并返回给前端
     * @param response 本次操作的响应流
     * @param workbook
     * @param fileName 文件名
     */
    public static void writeToResponse(HttpServletResponse response,Workbook workbook, String fileName) {
        OutputStream outputStream = null;
        try {
            setResponseHeader(response,fileName);
            outputStream = response.getOutputStream();
            //将excel文档写入到响应流中
            workbook.write(outputStream);
        } catch (IOException e) {}
        finally {
            try {
                if(outputStream != null){outputStream.close();}
                if(workbook != null) {workbook.close();}
            } catch (IOException e) {}
        }

    }

    /**
     * 设置reponse的头部信息
     * @param response 本次的响应流
     * @param fileName 文件名
     * @throws UnsupportedEncodingException
     */
    private static void setResponseHeader(HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "GBK"));
        response.setHeader("filename", URLEncoder.encode(fileName, "GBK"));
        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.setContentType("application/vnd.ms-excel;charset=GBK");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

}
