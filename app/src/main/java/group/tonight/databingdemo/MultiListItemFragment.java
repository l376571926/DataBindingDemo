package group.tonight.databingdemo;


import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MultiListItemFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Object> mUserList;

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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        mUserList = new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    public static final String[] nameArrays = {
            "熊大",
            "熊二",
            "张三",
            "李四",
            "王五",
            "赵六",
            "齐七",
            "柳八",
            "唐九",
            "钱十"
    };

    public static List<User> createUserList() {
        List<User> userList = new ArrayList<>();
        String imageUrl = "http://cms-bucket.nosdn.127.net/catchpic/9/9d/9dcb73e71fea7b746c50bc3874f8c706.jpg?imageView&thumbnail=550x0";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(nameArrays[random.nextInt(nameArrays.length)]);
            user.setAge(((int) (Math.random() * 100)));
            user.setAvatorUrl(imageUrl);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserList.clear();
        //banner
        mUserList.add(new Banner("http://cms-bucket.nosdn.127.net/catchpic/9/9d/9dcb73e71fea7b746c50bc3874f8c706.jpg?imageView&thumbnail=550x0"));
        //main
        mUserList.addAll(createUserList());

        mAdapter.notifyDataSetChanged();
    }

    private RecyclerView.Adapter<DataBoundViewHolder> mAdapter = new RecyclerView.Adapter<DataBoundViewHolder>() {
        @NonNull
        @Override
        public DataBoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final DataBoundViewHolder holder = DataBoundViewHolder.create(getLayoutInflater(), parent, viewType);

            holder.getBinding().addOnRebindCallback(new OnRebindCallback() {
                @Override
                public boolean onPreBind(ViewDataBinding binding) {
                    return mRecyclerView != null && mRecyclerView.isComputingLayout();
                }

                @Override
                public void onCanceled(ViewDataBinding binding) {
                    if (mRecyclerView == null || mRecyclerView.isComputingLayout()) {
                        return;
                    }
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        notifyItemChanged(position);
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull DataBoundViewHolder holder, int position) {
            holder.bindTo(mUserList.get(position));
        }

        @Override
        public void onBindViewHolder(@NonNull DataBoundViewHolder holder, int position, @NonNull List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);

        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }

        @Override
        public int getItemViewType(int position) {
            Object obj = mUserList.get(position);
            System.out.println(obj.toString());
            if (obj instanceof User) {
                System.out.println("加载用户资料布局--->" + position);
                return R.layout.simple_list_item_1;
            } else if (obj instanceof Banner) {
                // TODO: 2018/6/22  广告布局无法显示广告图片
                System.out.println("加载广告布局--->" + position);
                return R.layout.multi_item_list_header;
            }
            throw new RuntimeException("invalid obj");
        }
    };

}
