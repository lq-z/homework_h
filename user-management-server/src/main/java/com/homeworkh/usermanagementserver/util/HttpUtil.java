package com.homeworkh.usermanagementserver.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;

/**
 * @ Author     ：cym
 * @ Date       ：Created in 2019/8/26
 * @ Description：HttpUtils
 * @ Modified By：
 */

@Slf4j
public class HttpUtil {

    private static OkHttpClient CLIENT;

    static {
        OKHttpSSLUtil.SSLParams sslParams = null;
        try {
            sslParams = OKHttpSSLUtil.getSslSocketFactory(null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CLIENT = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //https
                .build();
    }

    // TODO: 2020/8/21 1400 格式
    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    @SuppressWarnings("all")
    public static String get(final String url) {
        log.info("报警 url :{}", url);
        Request request = new Request.Builder().url(url).get().build();
        Call call = CLIENT.newCall(request);
        try {
            String responseBody = call.execute().body().string();
            log.info("\n HTTP:\n METHOD: POST\n URL: {}\n REQUEST BODY: {}\n RESPONSE BODY: {}", url, responseBody);
            return responseBody;
        } catch (IOException e) {
            log.error("\n HTTP POST ERROR:\n URL: {}\n BODY: {}", url, e);
            return null;
        }
    }

    @SuppressWarnings("all")
    public static String post(final String url, RequestBody body) {
        log.info("报警 url :{}", url);
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = CLIENT.newCall(request);
        try {
            String responseBody = call.execute().body().string();
            log.info("\n HTTP:\n METHOD: POST\n URL: {}\n RESPONSE BODY: {}", url, responseBody);
            return responseBody;
        } catch (IOException e) {
            log.error("\n HTTP POST ERROR:\n URL: {}\n BODY: {}", url, body, e);
            return null;
        }
    }

    @SuppressWarnings("all")
    public static void asyncGet(final String url) {
        log.info("报警 url :{}", url);
        Request request = new Request.Builder().url(url).get().build();
        Call call = CLIENT.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("\n HTTP POST ERROR:\n URL: {}", url, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("\n HTTP:\n METHOD: POST\n URL: {}\n RESPONSE BODY: {}", url, response.body().string());
                return;
            }
        });
    }

    @SuppressWarnings("all")
    public static void asyncPost(final String url, RequestBody body) {
        log.info("报警 url :{}", url);
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = CLIENT.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("\n HTTP POST ERROR:\n URL: {}", url, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("\n HTTP:\n METHOD: POST\n URL: {}\n RESPONSE BODY: {}", url, response.body().string());
                return;
            }
        });
    }

    public static void main(String[] args) {
        String jsonBody = "{\"data\":\"Dpy6x9I/pi51NnEPMUFhXJMM+T5CJ++qdShH4m4oo9yjhQ+Hiza7gWZZB/Fp+lLbqP/x7S+uhgDXtUk3omf0O8PdCcGMsI04c7EzGck36H2OXRklI6NN/QgQdmsqW+ZoeWyjfMHS05Q0Gt7I4YZh4NDjgNOuflVCn55FiNdlK17+n6zEhaR7+T0IJhu1eUgUpudYT/m5ojLk+sg5V0HMO68yKdriyAym/pNfZITQJxkkroGcfYIBaaIPHUi/aviFWvsx27KsFTpHMPGxTuJjQ6wb7PsPfudX5hhXnueHBh+o0clKJSZCt6c1LiOU5D0/Zo+KWtTld99KbO2hLu7wFPqYy1heFVTE1HixzOzf3Q3F2XACmOz3Rmu/1XB/1Joz9P41/g8GM2P0GeX88H3X+52A2kNx5EK1GyHu8agPCfHQL3vCJNPUMmnNnFKlp5Wxs9Zy7uJ2J+U8N7daRUqXmjCkFXl+pRSfI5H6MVxtAQOR+/MCmDcO0tlEYxn0bu1WtLViBfmIBPguWM6MBfsf8sXpmQHnI3h6DkE4NWVA8WchfaK6UFN2uJ/eb47MC2OKBFzvdoeqRc6hxmz5lcOFTe8V2517B7Ol8LFBcz95lN3j9NoUVjcKT9NlmFN79rXoMoo4kJo0/6Y3TFV53pxmv1V6aRyFqjy//V4+IDhCHng=\",\"version\":\"1.0\",\"timestamp\":1596423771279,\"xid\":\"3c6a066d-cb3b-4dd8-8a6f-a493078d6d38\"}";
        RequestBody requestBody = RequestBody.create(HttpUtil.JSON_TYPE, String.valueOf(jsonBody));
//        post("https://api.xjiot.link/iptv/alert", requestBody);
        post("http://weilinlidev.babamagic.ltd/iptv/alert", requestBody);
    }


}
