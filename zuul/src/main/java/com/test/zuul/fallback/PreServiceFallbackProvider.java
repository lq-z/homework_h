package com.test.zuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author lq
 * @date 2020/10/19 10:26
 */
@Component
public class PreServiceFallbackProvider implements FallbackProvider {
    /**
     * * 为所有服务提供回退
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        if (cause instanceof HystrixTimeoutException){
//            return response(HttpStatus.GATEWAY_TIMEOUT);
//        }else{
//            return response(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("{\"code\":\"500\",\"msg\":\"微服务不可用，请稍后再试。\"}".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }

//    private ClientHttpResponse response(final HttpStatus status){
//        return new ClientHttpResponse() {
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                return HttpStatus.BAD_REQUEST;
//            }
//
//            @Override
//            public int getRawStatusCode() throws IOException {
//                return HttpStatus.BAD_REQUEST.value();
//            }
//
//            @Override
//            public String getStatusText() throws IOException {
//                return HttpStatus.BAD_REQUEST.getReasonPhrase();
//            }
//
//            @Override
//            public void close() {
//
//            }
//
//            @Override
//            public InputStream getBody() throws IOException {
//                return new ByteArrayInputStream("{\"code\":\"500\",\"msg\":\"微服务不可用，请稍后再试。\"}".getBytes());
//            }
//
//            @Override
//            public HttpHeaders getHeaders() {
//                HttpHeaders httpHeaders = new HttpHeaders();
//                MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
//                httpHeaders.setContentType(mediaType);
//                return httpHeaders;
//            }
//        };
//    }
}
