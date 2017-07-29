package com.ibeesaas.demo.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Http请求封装类，提供了doPost和doGet方法。
 */
public class HttpClientUtil {

    public static String doPost(String url,String bodyJson,String token){
        //创建httpclient对象 
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        try{  
        	//创建post方式请求对象
            HttpPost httpPost = new HttpPost();
        	httpPost.setURI(new URI(url));

            if(bodyJson!=null&&bodyJson!=""){
                //设置body参数  
                StringEntity entity=new StringEntity(bodyJson,Config.DEF_CHATSET);
                entity.setContentEncoding(Config.DEF_CHATSET);
                entity.setContentType(Config.CONTENTTYPE);
                httpPost.setEntity(entity);
            }
            if(token!=null&&token!=""){
                //设置header信息
                httpPost.setHeader(Config.IBEEAUTH_TOKEN,token);
            }
    
            //执行请求操作，并拿到结果（同步阻塞） 
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                	//按指定编码转换结果实体为String类型  
                    result = EntityUtils.toString(resEntity,Config.DEF_CHATSET);
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace(); 
        } finally {
            try {
                //关闭流并释放资源
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;  
    }  
    
	public static String doGet(String url,String token) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultString = "";
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			
			//设置header信息
			if(token!=null&&token!=""){
				httpGet.setHeader(Config.IBEEAUTH_TOKEN,token);
	         }
			// 执行请求
            CloseableHttpResponse response = httpclient.execute(httpGet);
			// 按指定编码转换结果实体为String类型  
			if (response!=null) {
				resultString = EntityUtils.toString(response.getEntity(), Config.DEF_CHATSET);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
                //关闭流并释放资源
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
}
