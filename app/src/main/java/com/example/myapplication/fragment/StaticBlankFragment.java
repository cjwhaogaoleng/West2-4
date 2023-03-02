package com.example.myapplication.fragment;

import android.media.Rating;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity_StaticFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticBlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization p                                                             arameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RadioButton radioLike;
    private RadioButton radioDislike;
    private RatingBar ratingBar;
    private TextView textView, textView2;



    public StaticBlankFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaticBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaticBlankFragment newInstance(String param1, String param2) {
        StaticBlankFragment fragment = new StaticBlankFragment();
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
        return inflater.inflate(R.layout.fragment_static_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioLike = view.findViewById(R.id.rb_like);
        radioDislike = view.findViewById(R.id.rb_dislike);
        ratingBar = view.findViewById(R.id.rb_star);
        textView = view.findViewById(R.id.tv_ifLike);
        textView2 = view.findViewById(R.id.tv);

        radioLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText("喜欢app");
                }
            }
        });


        radioDislike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText("不喜欢app");
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    Toast.makeText(getActivity(),"打了 "+ rating+" 分",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((Activity_StaticFragment)getActivity()).setOnDataChangeListener(new Activity_StaticFragment.OnDataChangeListener() {
            @Override
            public void sentData(String data) {
               if (data!=null){
                   textView2.setText(data);
               }
            }

            @Override
            public String getData(String data) {
                return "我来自fragment";
            }
        });
    }

}