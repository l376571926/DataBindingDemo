package group.tonight.databingdemo;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MultiListItemFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public static MultiListItemFragment getInstance() {
        return new MultiListItemFragment();
    }

    public MultiListItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_multi_list_item, container, false);
        View root = inflate.getRoot();

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
        return root;
    }

}
