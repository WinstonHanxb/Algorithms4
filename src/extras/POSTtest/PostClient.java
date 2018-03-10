package extras.POSTtest;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class PostClient
{
    private static void  sendPost(String url,String josn){
       try{
           DefaultHttpClient httpClient = new DefaultHttpClient();
           HttpPost method = new HttpPost(url);
           method.setHeader("Content-type","application/json;charset=utf-8");
           method.setHeader("Accept","application/json");
           method.setEntity(new StringEntity(josn,"UTF-8"));
           long startTime = System.currentTimeMillis();
           HttpResponse response = httpClient.execute(method);
           long endTime = System.currentTimeMillis();
           int statusCode = response.getStatusLine().getStatusCode();
           String statusContent = response.getStatusLine().getReasonPhrase();
           System.out.println("code:" + statusCode);
           System.out.println("reason: "+statusContent);
       }catch (Exception e){
            e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        String url = "http://127.0.0.1:9000/maintenanceRecords/";
        testObj test = new testObj(1L,2L,"测试","测试字符串");
        JSONObject josn = JSONObject.fromObject(test);
        System.out.println("json" + josn);
        sendPost(url,josn.toString());
    }


}
