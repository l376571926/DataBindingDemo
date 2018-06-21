package group.tonight.databingdemo;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleTextFragment extends Fragment {

    public static SimpleTextFragment getInstance() {
        return new SimpleTextFragment();
    }

    public SimpleTextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //布局一定要用<layout></layout>标签包起来，否则这里inflate出来为null
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_text, container, false);
//        if (getContext() != null) {
//            if (inflate == null) {
//                Toast.makeText(getContext().getApplicationContext(), "inflate为空", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(getContext().getApplicationContext(), "inflate不为空", Toast.LENGTH_SHORT).show();
//            }
//        }
        User user = new User();
        user.setName("欢迎使用DataBinding数据绑定库");
        inflate.setVariable(BR.user, user);
        return inflate.getRoot();
    }

}
