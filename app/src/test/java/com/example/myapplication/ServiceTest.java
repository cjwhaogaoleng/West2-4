package com.example.myapplication;

import com.example.myapplication.projectJava.Service;
import com.example.myapplication.projectJava.UserResponds.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServiceTest {


    Retrofit retrofit;
    Service service;


    @Test
    public void testRegisterServiceTest() throws JSONException {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.183:8080/Assessment4/").build();

        User user = new User("000", "hyk", "123", 20);
        System.out.println(user.toString());
        Gson gson = new Gson();
        String json = gson.toJson(user);


        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);


        service = retrofit.create(Service.class);

        retrofit2.Call<ResponseBody> call = service.register(requestBody);

        try {
            Response<ResponseBody> bodyResponse = call.execute();
            gson.fromJson(String.valueOf(bodyResponse), String.class);
            System.out.println(bodyResponse);

            if (bodyResponse.isSuccessful()) {
                System.out.println(bodyResponse.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void testLoginServiceTest() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.183:8080/Assessment4/").build();
        service = retrofit.create(Service.class);
        retrofit2.Call<ResponseBody> call = service.login("123", "222");
        try {
            Response<ResponseBody> bodyResponse = call.execute();
            System.out.println(bodyResponse);
            if (bodyResponse.isSuccessful()) {
                System.out.println(bodyResponse.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @Test
    public void testRegisterServiceTest2() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        User user = new User("000", "hyk", "123", 20);
        Gson gson = new Gson();
        String json = gson.toJson(user);

        //JSONObject result = new JSONObject(String.valueOf(user));
//            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), String.valueOf(user));
        //result.get("application/json");

        //RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        RequestBody requestBody1 = FormBody.create(MediaType.parse("application/json"), json);

//        new FormBody.Builder().add().addEncoded()


        Request request = new Request.Builder().url("http://192.168.43.183:8080/Assessment4/register").post(requestBody1).build();

        Call call = okHttpClient.newCall(request);

        try {
            okhttp3.Response response = call.execute();
            System.out.println(response);

            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testPostDocTest() throws JSONException {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.183:8080/Assessment4/").build();

        User user = new User("000", "hyk", "123", 20);
        System.out.println(user.toString());
        Gson gson = new Gson();
        String json = gson.toJson(user);


        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);


        service = retrofit.create(Service.class);

        retrofit2.Call<ResponseBody> call = service.register(requestBody);

        try {
            Response<ResponseBody> bodyResponse = call.execute();
            System.out.println(bodyResponse);
            if (bodyResponse.isSuccessful()) {
                System.out.println(bodyResponse.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
