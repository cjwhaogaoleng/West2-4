package com.example.myapplication.projectJava;

import android.graphics.ColorSpace;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import com.example.myapplication.projectJava.UserResponds.Proposer;
import com.example.myapplication.projectJava.UserResponds.User;

import org.json.JSONObject;
import java.net.URL;

public interface Service {

    //登录
    @GET("login/test")
    Call<ResponseBody> login(@Query("userId") String userId, @Query("password") String password);


    //注册
    @POST("register")
    Call<ResponseBody> register(@Body RequestBody user);
    //Body 传json数据类型 由user转化

//    查看项目
    @GET("project")
    Call<ResponseBody> check  (@Query("portion") String portion, @Query("password") ColorSpace.Model model);


//    众筹者上传材料

    @POST("proposer/upload")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("proposer") Proposer proposer);

}
