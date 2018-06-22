package group.tonight.databingdemo;


import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleImageFragment extends Fragment {
    public static final String TAG = SimpleImageFragment.class.getSimpleName();

    public static SimpleImageFragment getInstance() {
        return new SimpleImageFragment();
    }

    public SimpleImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_image, container, false);
        //加载本地图片
        inflate.setVariable(BR.localImageId, R.drawable.face);
        //加载网络图片
        inflate.setVariable(BR.remoteImageUrl, "http://cms-bucket.nosdn.127.net/catchpic/9/9d/9dcb73e71fea7b746c50bc3874f8c706.jpg?imageView&thumbnail=550x0");

        return inflate.getRoot();
    }

    @BindingAdapter(value = {"android:src"})
    public static void setSrc(ImageView imageView, Object src) {
        if (src instanceof String) {
            RequestManager requestManager = Glide.with(imageView);
            Log.e(TAG, "line 51 setSrc: " + src);
            requestManager
                    .load(((String) src))
                    .into(imageView);
        } else if (src instanceof Integer) {
            Log.e(TAG, "line 56 setSrc: " + src);
            imageView.setImageResource(((Integer) src));
        }
    }
}
