package extras.ExcelReaders;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.*;
import java.util.HashMap;

public class ExcelReader {
    public static void main(String args[]) {
        try {
            String path = "E:\\研究生\\GitHub\\Algorithms4\\src\\extras\\ExcelReaders\\excels.xls";
            InputStream is = new FileInputStream(path);
            Workbook book = Workbook.getWorkbook(is);
            // 获得第一个工作表对象
            Sheet sheet1 = book.getSheet(0);
            int rows = sheet1.getRows();
            HashMap<String,String> res = new HashMap<>();
            for (int row = 0; row < rows; row++) {
                cellObjects obj = new cellObjects(sheet1,row);
                stringMethods(res, obj.mainRoutes,obj.mainFreq);
                stringMethods(res, obj.spareRoutes,obj.spareFreq);
            }
            book.close();
            is.close();
            OutputStream os = new FileOutputStream(path);
            WritableWorkbook wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("站点波道表",1);
            int row = 0;
            for (String node : res.keySet()) {
                Label label1 = new Label(0,row,node);
                Label label2 = new Label(1,row,res.get(node));
                ws.addCell(label1);
                ws.addCell(label2);
                row++;
            }
            wwb.write();
            wwb.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void stringMethods(HashMap<String, String> res, String[] routes,String freq) {
        if(routes != null){
            for (int i = 0; i < routes.length; i++) {
                String tempFreqs;
                if(res.containsKey(routes[i])){
                    tempFreqs = res.get(routes[i]);
                    if(!tempFreqs.contains(freq)) tempFreqs = tempFreqs + freq + "、";
                }
                else{
                    tempFreqs = freq + "、";
                }
                res.put(routes[i],tempFreqs);
            }
        }
    }
}
class cellObjects{
    public String[] mainRoutes;
    public String[] spareRoutes;
    public String mainFreq;
    public String spareFreq;
    public cellObjects(Sheet sheet,int row) {
        String mainRoute = sheet.getCell(1,row).getContents();
        if(mainRoute.equals("null")) mainRoutes=null;
        else mainRoutes = mainRoute.split("-");
        mainFreq = sheet.getCell(2,row).getContents();
        String spareRoute = sheet.getCell(3,row).getContents();
        if(spareRoute.equals("null")) spareRoutes=null;
        else spareRoutes = spareRoute.split("-");
        spareFreq = sheet.getCell(4,row).getContents();
    }
}
