package com.cm.zhuoyue.common.utils.client;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author 陈萌
 * @date 2021/7/4 15:42
 */
public class HttpClientUtil {

    public static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private static MultiThreadedHttpConnectionManager connectionManager;

    private static HttpClient client;

    /**
     * 最大主机连接数
     */
    private static int maxHostConnections = 500;

    /**
     * 最大总连接数
     */
    private static int maxTotalConnections = 500;

    /**
     * 链接超时时间
     */
    private static int connectionTimeOut = 3000;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setDefaultMaxConnectionsPerHost(maxHostConnections);
        params.setMaxTotalConnections(maxTotalConnections);
        params.setConnectionTimeout(connectionTimeOut);
        params.setSoTimeout(connectionTimeOut);
        connectionManager.setParams(params);
        client = new HttpClient(connectionManager);
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    }

    /**
     * 修改超时时间
     *
     * @param connectionTimeOut 连接超时时间
     * @param soTimeout         socket超时时间
     */
    public static void setTimeOut(int connectionTimeOut, int soTimeout) {
        HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
        params.setConnectionTimeout(connectionTimeOut);
        params.setSoTimeout(soTimeout);
        log.info("http请求超时时间修改（" + connectionTimeOut + "），socket超时时间修改（" + soTimeout + "）");
    }

    /**
     * 将MAP转换成HTTP请求参数
     */
    public static NameValuePair[] praseParameterMap(Map<String, String> map) {
        NameValuePair[] nvps = new NameValuePair[map.size()];
        Set<String> keys = map.keySet();
        int i = 0;
        for (String key : keys) {
            nvps[i] = new NameValuePair();
            nvps[i].setName(key);
            nvps[i].setValue(map.get(key));
            i++;
        }
        return nvps;
    }

    public static boolean setRequestHeader(HttpMethodBase method, Map<String, String> headerMap) {
        if (headerMap == null || headerMap.size() == 0) {
            return false;
        }
        headerMap.forEach(method::setRequestHeader);
        return true;
    }

    /**
     * 发送post或get请求获取响应信息
     *
     * @param method http请求类型,post或get请求
     * @return 服务器返回的信息
     */
    public static String getResponseStr(HttpMethodBase method, Object args) {
        StringBuilder sb = new StringBuilder();
        URI url = null;
        long beginTimes = System.currentTimeMillis();
        try {
            //声明
            ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
            //加入相关的https请求方式
            Protocol.registerProtocol("https", new Protocol("https", fcty, 443));

            url = method.getURI();
            int statusCode = client.executeMethod(method);
            if (statusCode != 200) {
                log.error("HttpClient begin at " + beginTimes + ", HttpClient Error : statusCode = " + statusCode
                        + ", uri :" + method.getURI());
                return "";
            }
            // 以流的行式读入，避免中文乱码
            InputStream is = method.getResponseBodyAsStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            while ((str = dis.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {
            log.error("HttpClient begin at " + beginTimes + ", 调用远程出错;发生网络异常! uri :" + url + " params: " + args, e);
            // e.printStackTrace();
        } finally {
            // 关闭连接
            method.releaseConnection();
        }
        log.info("HttpClient begin at " + beginTimes + ", cost " + (System.currentTimeMillis() - beginTimes) + " ms," + " request url " + url + " , params: " + args);
        return sb.toString();
    }

    private static String parseNameValuePair(NameValuePair[] nv) {
        StringBuilder sb = new StringBuilder();
        if (nv == null || nv.length == 0) {
            return null;
        }
        for (NameValuePair kv : nv) {
            sb.append(kv.getName()).append("=").append(kv.getValue()).append("&");
        }
        return sb.toString();
    }

    private static String parseNameValuePair(List<NameValuePair[]> values) {
        StringBuilder sb = new StringBuilder();
        if (values == null || values.size() == 0) {
            return null;
        }
        for (NameValuePair[] value : values) {
            sb.append(parseNameValuePair(value));
        }
        return sb.toString();
    }

    public static String requestGet(String url, NameValuePair[] values) {
        GetMethod getMethod = new GetMethod(url);
        getMethod.setQueryString(values);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        return getResponseStr(getMethod, parseNameValuePair(values));
    }

    public static String requestGet(String url) {
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        return getResponseStr(getMethod, null);
    }

    public static String requestGet(String url, int outTime) {
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        return getResponseStr(getMethod, null);
    }

    /**
     * 使用get方式调用，传入头参数。
     *
     * @return 调用得到的字符串
     */
    public static String httpClientGet(String url, Map<String, String> headerMap) {
        GetMethod getMethod = new GetMethod(url);
        setRequestHeader(getMethod, headerMap);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        return getResponseStr(getMethod, null);
    }

    /**
     * 使用post方式调用
     *
     * @param url    调用的URL
     * @param values 传递的参数值List
     * @return 调用得到的字符串
     */
    public static String httpClientPost(String url, List<NameValuePair[]> values) {
        PostMethod postMethod = new PostMethod(url);
        //将表单的值放入postMethod中
        for (NameValuePair[] value : values) {
            postMethod.addParameters(value);
        }
        return getResponseStr(postMethod, parseNameValuePair(values));
    }

    /**
     * 使用post方式调用
     *
     * @param url    调用的URL
     * @param values 传递的参数值
     * @return 调用得到的字符串
     */
    public static String httpClientPost(String url, NameValuePair[] values) {
        List<NameValuePair[]> list = new ArrayList<>();
        list.add(values);
        return httpClientPost(url, list);
    }

    public static String httpClientPostByStream(String url, String stream) {
        return httpClientPostByStream(url, stream, "application/x-www-form-urlencoded");
    }

    public static String httpClientPostByStream(String url, String stream, String contentType) {
        PostMethod postMethod = null;
        RequestEntity reis;
        InputStream input = null;
        InputStream is = null;
        BufferedReader dis = null;
        StringBuilder res = new StringBuilder();
        long beginTimes = System.currentTimeMillis();
        try {
            postMethod = new PostMethod(url);
            //application/x-www-form-urlencoded
            postMethod.addRequestHeader("Content-Type", contentType);
            input = new ByteArrayInputStream(stream.getBytes(StandardCharsets.UTF_8));
            reis = new InputStreamRequestEntity(input);
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            postMethod.setRequestEntity(reis);
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != 200) {
                log.error("HttpClient begin at " + beginTimes + ", HttpClient Error : statusCode = " + statusCode + ", uri :" + postMethod.getURI());
                return "";
            }
            // 以流的行式读入，避免中文乱码
            is = postMethod.getResponseBodyAsStream();
            dis = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            while ((str = dis.readLine()) != null) {
                res.append(str);
            }
        } catch (Exception e) {
            log.error("invoke http error!", e);
        } finally {
            try {
                if (postMethod != null) {
                    postMethod.releaseConnection();
                }
                if (dis != null) {
                    dis.close();
                }
                if (is != null) {
                    is.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("HttpClient begin at {}, cost {} ms, request url {} , post stream: {}， result：{}", beginTimes, (System.currentTimeMillis() - beginTimes), url, stream, res);
        return res.toString();
    }

    /**
     * 通过图片的url获取图片的base64字符串
     *
     * @param imgUrl 图片url
     * @return 返回图片base64的字符串
     */
    public static String image2Base64(String imgUrl) {
        URL url;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();
            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = is.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            return Base64.getEncoder().encodeToString(outStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        return imgUrl;
    }

    /**
     * 使用post方式调用
     *
     * @param url        调用的URL
     * @param paramsJson 传递的参数值json格式
     * @return JsonObject
     */
    public static JSONObject httpClientPost(String url, JSONObject paramsJson, Map<String, String> headerMap) {
        PostMethod postMethod = new PostMethod(url);
        RequestEntity entity = null;
        try {
            entity = new StringRequestEntity(paramsJson.toJSONString(), "application/json", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("参数解析异常！", e);
        }
        postMethod.setRequestEntity(entity);
        setRequestHeader(postMethod, headerMap);
        log.info("url: {}, 参数: {}, 请求头：{}", url, paramsJson, headerMap);
        String responseStr = getResponseStr(postMethod, paramsJson.toJSONString());
        JSONObject jsonObject = new JSONObject();
        return jsonObject.getJSONObject(responseStr);
    }

    public static JSONObject httpClientPost(String url, JSONObject paramsJson) {
        return httpClientPost(url, paramsJson, null);
    }

}
