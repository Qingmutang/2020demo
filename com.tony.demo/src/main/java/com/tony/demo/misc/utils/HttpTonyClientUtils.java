package com.tony.demo.misc.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.tony.demo.misc.utils.xml.XMLParse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpTonyClientUtils {
	private static CloseableHttpClient httpClient = null;

    static {
        try {
            HttpClientBuilder custom = HttpClients.custom();
            Properties prop = PropertiesLoaderUtils.loadAllProperties("config.properties");
            if (Boolean.valueOf(prop.getProperty("http.proxySet")))
                custom.setProxy(new HttpHost(prop.getProperty("http.proxyHost"), Integer.parseInt(prop.getProperty("http.proxyPort"))));
            httpClient = custom.build();
        } catch (Exception e) {
            log.error("initializing SSL failed when loading client certificate", e);
        }
    }

    /**
     * get请求
     * @param url 请求地址
     * @param accessToken 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），
     *                    也简称为令牌
     * @param clazz 返回的实体类
     * @return
     */
    @Deprecated
    public static <T> T executeGet(String url, String accessToken,
                             Class<T> clazz) throws IOException {
        HttpGet httpGet = new HttpGet(String.format(url, accessToken));
        addHeader(httpGet);
        log.info("requestUrl-> {}", httpGet.getURI());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String text = EntityUtils.toString(entity);
        log.info("response massage-> {}", text);
        response.close();
        return JSONObject.parseObject(text, clazz);
    }

    /**
     * get请求
     * 返回值为xml
     * @throws IOException
     */
    public static <T> T executeGet(String url, Class<T> clazz) throws IOException {
        HttpGet httpGet = new HttpGet(url); addHeader(httpGet);
        log.info("requestUrl-> {}", httpGet.getURI());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String text = EntityUtils.toString(entity);
        log.info("response massage-> {}", text);
        response.close();
        try {
            return XMLParse.toBean(text, clazz);
        } catch (JAXBException e) {
            log.error("convert XML exception", e);
        }
        return null;
    }

    /**
     * post请求
     * @param url 请求地址
     * @param accessToken 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），
     *                    也简称为令牌
     * @param clazz 返回的实体类
     * @return
     */
    @Deprecated
    public static <T> T executePost(String url, String request,
                              String accessToken, Class<T> clazz) throws IOException {
        HttpPost httpPost = new HttpPost(String.format(url, accessToken)); addHeader(httpPost);
        log.info("requestUrl-> {} params-> {}", httpPost.getURI(), request);
        httpPost.setEntity(new StringEntity(request));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String text = EntityUtils.toString(entity);
        log.info("response massage-> {}", text);
        response.close();
        return JSONObject.parseObject(text, clazz);
    }

    /**
     * post上传文件
     * @param clazz 要返回的类
     * @param url 请求地址
     * @param obj form-data参数
     * @param requests ?后的参数
     * @throws IOException
     */
    public static <T> T executePost(Class<T> clazz, String url,
                        Object obj, String... requests) throws IOException {
        HttpPost httpPost = new HttpPost(String.format(url, requests));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        try {
            setFormData(obj, builder);
        } catch (IllegalAccessException e) {
            log.error("设置文件form-data异常", e);
        }
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String resp = EntityUtils.toString(response.getEntity());
        return JSONObject.parseObject(resp, clazz);
    }

    /**
     * post请求 获取图片
     * @param url 请求地址
     * @param request 字符参数
     * @throws IOException
     */
    public static byte[] executePost(String url, String request) throws IOException {
        HttpPost httpPost = new HttpPost(url); addHeader(httpPost);
        log.info("requestUrl-> {} params-> {}", httpPost.getURI(), request);
        if (StringUtils.isNotBlank(request))
            httpPost.setEntity(new StringEntity(request));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        byte[] bytes = null; BufferedImage image = ImageIO.read(is);
        if (ObjectUtils.isEmpty(image)) {
            log.info("response massage-> {}" , EntityUtils.toString(entity));
        } else {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", os); bytes= os.toByteArray();
            log.info("response massage-> {}" , Base64.encodeBase64String(bytes));
        }
        response.close();
        return bytes;
    }

    /**
     * 无参获取图片
     * @param url
     * @return
     * @throws IOException
     */
    public static byte[] executePost(String url) throws IOException {
        return executePost(url, null);
    }

    /**
     * post请求
     * @param url 请求地址
     * @param request 参数字符为xml
     */
    public static <T> T executePost(String url, String request, Class<T> clazz) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(request));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            log.info("http request success url-> {}, result->\n{}", url, result);
            return XMLParse.toBean(result, clazz, Boolean.TRUE);
        } catch (JAXBException e) {
            log.info("解析xml失败", e);
        } catch (DocumentException e) {
            log.error("修改xml根节点名称出错", e);
        }
        return null;
    }

    private static void setFormData(Object obj, MultipartEntityBuilder builder)
            throws IllegalAccessException {
        Class aClass = obj.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(obj) instanceof File)
                builder.addBinaryBody(field.getName(), (File) field.get(obj));
        }
    }

    private static void addHeader(HttpMessage httpMessage) {
        httpMessage.addHeader("Accept", "application/json");
        httpMessage.addHeader("Accept-Encoding", "gzip, deflate");
        httpMessage.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-us;q=0.5,en;q=0.3,*;q=0.2");
        httpMessage.addHeader("Connection", "keep-alive");
        httpMessage.addHeader("Cache-Control", "max-age=0");
        httpMessage.addHeader("Pragma", "no-cache");
        httpMessage.addHeader("User-Agent", "Mozilla/5.0");
        httpMessage.addHeader("Content-Type", "application/json");
    }
    
    @SuppressWarnings("resource")
	public static <T> T postImage(String imageUrl,String url, Class<T> clazz) {
    	
    	CloseableHttpResponse response = null;
    	String resp="";
    	InputStream stream=null;
		try {
			
			HttpGet httpGet = new HttpGet(imageUrl);
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			stream= entity.getContent();
			
			
			HttpPost post = new HttpPost(url);
		    HttpEntity dataEntity = getMultiArrayEntity(stream);//文件流格式上传

		    post.setEntity(dataEntity);
		    response = httpClient.execute(post);

		    resp = EntityUtils.toString(response.getEntity());
		    log.info("上传图片获取结果：{}",resp); 
	
		} catch (Exception e) {
			  log.info("getImage 失败:{}", e);
		
		}finally {
			 try {
				 if(response!=null) {
					 response.close();
				 }
			} catch (IOException e) {
				log.info("response close 失败:{}", e);
			}
		}
		if(StringUtils.isEmpty(resp)) {
			return null;
		}
	 return JSONObject.parseObject(resp, clazz);
    	
    }
    
	 /**
	  * byte数组格式上传
	 */
	 public static HttpEntity getMultiArrayEntity(InputStream stream) throws Exception{
	     MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        int len = 1024;
	        byte tmp [] = new byte[len];
	        int i ;
	        while((i=stream.read(tmp, 0, len))>0){
	            baos.write(tmp, 0, i);
	        }
	     byte[] byteArr = baos.toByteArray();
	     String fileName = UUID.randomUUID()+".jpg";
	     builder.addBinaryBody("file", byteArr, ContentType.DEFAULT_BINARY, fileName);
	     return builder.build();
	 }
	 
	    public static String executePostgetBase64(String url, String request) throws IOException {
	        HttpPost httpPost = new HttpPost(url); addHeader(httpPost);
	        log.info("requestUrl-> {} params-> {}", httpPost.getURI(), request);
	        if (StringUtils.isNotBlank(request))
	            httpPost.setEntity(new StringEntity(request));
	        CloseableHttpResponse response = httpClient.execute(httpPost);
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	        int len = 1024;
	        byte tmp [] = new byte[len];
	        int i ;
	        while((i=is.read(tmp, 0, len))>0){
	            baos.write(tmp, 0, i);
	        }
	        byte[] byteArr = baos.toByteArray();
	        log.info("base64-> {}", Base64.encodeBase64String(byteArr));
	        response.close();
	        return Base64.encodeBase64String(byteArr);
	    }
}
