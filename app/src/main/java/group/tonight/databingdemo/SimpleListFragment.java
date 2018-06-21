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


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleListFragment extends Fragment {

    private List<User> mUserList;
    private RecyclerView mRecyclerView;

    public static SimpleListFragment getInstance() {
        return new SimpleListFragment();
    }

    public SimpleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_list, container, false);

        View root = inflate.getRoot();
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));
        mUserList = new ArrayList<>();
        mRecyclerView.setAdapter(mUserViewHolderAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                String imageUrl = "http://cms-bucket.nosdn.127.net/catchpic/9/9d/9dcb73e71fea7b746c50bc3874f8c706.jpg?imageView&thumbnail=550x0";

                mUserList.clear();
                for (int i = 0; i < 100; i++) {
                    User user = new User();
                    user.setName("item：" + i);
                    user.setAge(((int) (Math.random() * 100)));
                    user.setAvatorUrl(imageUrl);
                    mUserList.add(user);
                }
                mUserViewHolderAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        static UserViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.simple_list_item_1, parent, false);
            return new UserViewHolder(inflate);
        }

        private ViewDataBinding mViewDataBinding;

        public ViewDataBinding getBinding() {
            return mViewDataBinding;
        }

        public UserViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
        }

        public void bindTo(User user) {
            mViewDataBinding.setVariable(BR.user, user);
            mViewDataBinding.executePendingBindings();
        }
    }

    private RecyclerView.Adapter<UserViewHolder> mUserViewHolderAdapter = new RecyclerView.Adapter<UserViewHolder>() {
        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final UserViewHolder holder = UserViewHolder.create(getLayoutInflater(), parent);

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
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.bindTo(mUserList.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    };
}
