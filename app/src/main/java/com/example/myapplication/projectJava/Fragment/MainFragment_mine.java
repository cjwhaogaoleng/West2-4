package com.example.myapplication.projectJava.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.projectJava.Activity.Activity_perfectInfor;
import com.example.myapplication.R;
import com.example.myapplication.projectJava.Activity.activity_login;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment_mine#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment_mine extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int REQUEST_CODE_MAP_STORAGE = 2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View rootView;

    private Button btn_exit, btn_perfect;
    private ImageView iv_mine;

    private TextView  tvCity, tvBirthday, tvName;


    public MainFragment_mine() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPager1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment_mine newInstance(String param1, String param2) {
        MainFragment_mine fragment = new MainFragment_mine();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main_mine, container, false);
        }
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), activity_login.class));
                getActivity().finish();
            }
        });

        btn_perfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Activity_perfectInfor.class));
            }
        });

//        iv_mine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*打开图库*/
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                //处理返回集
//                startActivityForResult(intent, REQUEST_CODE_MAP_STORAGE);
//
//
//                /*打开相机*/
//                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////处理返回集
//                startActivityForResult(intent, 1);
//
//            }
//        });


      /*  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 1://拍照处理
                    //将拍下来的照片解析成Bitmap对象并显示
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    person_set_header.setImageBitmap(bitmap);
                    break;
                case 2://相册处理
                    Uri path = data.getData();
                    //利用框架加载图片资源
                    Glide.with(getApplicationContext()).load(path).into(person_set_header);
                    break;
            }*/
    }

    //跳转回来后
    @Override
    public void onResume() {
        super.onResume();
        getDataFromSPF();
    }

    private void getDataFromSPF() {
        /**
         *         edit.putString("name", name);
         *         edit.putString("phone", phone);
         *         edit.putString("id", id);
         *
         *         edit.putString("birthdayTime", birthdayTime);
         *         edit.putString("city", selectedCity);
         *         edit.putString("gender", gender);
         *         edit.putString("age", age);
         */
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "用户");
        String city = sharedPreferences.getString("city", "北京");
        String birthdayTime = sharedPreferences.getString("birthdayTime", "2000年1月1日0时0分");
        String gender = sharedPreferences.getString("gender", "男");
        String age = sharedPreferences.getString("age", "23");

        tvBirthday.setText(birthdayTime);
        tvCity.setText(city);
        tvName.setText(name);

    }


    private void initView(View view) {
        btn_exit = view.findViewById(R.id.btn_exit);
        iv_mine = view.findViewById(R.id.img_mine);
        btn_perfect = view.findViewById(R.id.btn_perfect_information);

        tvCity = view.findViewById(R.id.tv_city_text);
        tvBirthday = view.findViewById(R.id.tv_birthday_text);
        tvName = view.findViewById(R.id.tv_name);

    }

}