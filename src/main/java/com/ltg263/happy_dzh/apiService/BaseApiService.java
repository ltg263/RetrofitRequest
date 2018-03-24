package com.ltg263.happy_dzh.apiService;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 */
public interface BaseApiService {
    @POST
    Call<ResponseBody> post(@Url String url, @QueryMap Map<String, String> params);
    @Multipart
    @POST
    Call<ResponseBody> postPart(@Url String url, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part part);
    @Multipart
    @POST
    Call<ResponseBody> postPart(@Url String url, @Part MultipartBody.Part part);
    @POST
    Call<ResponseBody> post(@Url String url, @Body MultipartBody body);
    @POST
    Call<ResponseBody> post(@Url String url, @Body Object body);
    @POST
    Call<ResponseBody> post(@Url String url);
    @GET
    Call<ResponseBody> get(@Url String url, @QueryMap Map<String, String> params);
    @GET
    Call<ResponseBody> get(@Url String url);
}
