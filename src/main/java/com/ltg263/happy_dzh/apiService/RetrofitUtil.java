package com.ltg263.happy_dzh.apiService;

import com.google.gson.Gson;
import com.ltg263.happy_dzh.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 */
public class RetrofitUtil {

    private static Retrofit mRetrofit;
    private static Gson gson;

    static {
        gson = new Gson();
        //初始化
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl("http://v.juhe.cn/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
                .addConverterFactory(ScalarsConverterFactory.create()) // 使用String作为数据转换器
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
                .build();
    }

    /**
     * 通用的接口请求
     *
     * @param params
     * @param clz
     * @param callBack
     * @param <T>
     */
    public static <T> void request(RxHttpParams params, final Class<T> clz, final HttpCallBackImpl<T> callBack) {
        if (params == null || clz == null) {
            return;
        }
        BaseApiService apiService = mRetrofit.create(BaseApiService.class);
        String url = params.getUrl();
        Call<ResponseBody> call;

       if (params.getParamsBody() != null) {
            call = apiService.post(url, params.getParamsBody());
        } else if (params.getPartParams().isEmpty() && params.getQueryParams().isEmpty()
                && params.getPart() == null) {
            if (params.getMethod() == RxHttpParams.HttpMethod.POST) {
                call = apiService.post(url);
            } else {
                call = apiService.get(url);
            }
        } else if (params.getPartParams().isEmpty() && params.getPart() == null) {
            if (params.getMethod() == RxHttpParams.HttpMethod.POST) {
                call = apiService.post(url, params.getQueryParams());
            } else {
                call = apiService.get(url, params.getQueryParams());
            }
        } else if (params.getQueryParams().isEmpty()) {
            if (params.getPartParams().isEmpty()) {
                call = apiService.postPart(url, params.getPart());
            } else {
                call = apiService.postPart(url, params.getPartParams(), params.getPart());
            }
        } else {
            call = apiService.post(url, params.getMultipartBody());
        }
        call.enqueue(new Callback() {
                         @Override
                         public void onResponse(Call call, retrofit2.Response response) {
                             ResponseBody body = (ResponseBody) response.body();
                             InputStream is = body.byteStream();
                             if (body != null && is != null) {
                                 String jsonStr = new String(read(body.byteStream()), Charset.forName("UTF-8"));
                                 LogUtil.w("jsonStr:"+jsonStr);
                                 if(clz==null){
                                     callBack.onFinish();
                                     callBack.onError(jsonStr);
                                     return;
                                 }
                                 T t;
                                 if (clz == String.class) {
                                     t = (T) jsonStr;
                                 } else {
                                     t = gson.fromJson(jsonStr, clz);
                                 }
                                 if (callBack != null) {
                                     callBack.onFinish();
                                     callBack.onCompleted(t);
                                 }

                             } else {
                                 if (callBack != null) {
                                     callBack.onFinish();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call call, Throwable t) {
                             if (callBack != null) {
                                 callBack.onFinish();
                                 callBack.onError(t.getMessage());
                             }
                         }
                     }

        );
    }

    public abstract static class HttpCallBackImpl<T> {
        /**
         * 请求返回的对象
         * @param t：对象
         */
        public abstract void onCompleted(T t);

        /**
         * 请求完成
         */
        public void onFinish() {
        }

        /**
         *  请求失败
         * @param message
         */
        public void onError(String message) {
            LogUtil.w("message:"+message);
        }

    }

    private static byte[] read(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
