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

        mUserList.clear();
        mUserList.addAll(MultiListItemFragment.createUserList());
        mUserViewHolderAdapter.notifyDataSetChanged();
    }

    private RecyclerView.Adapter<DataBoundViewHolder> mUserViewHolderAdapter = new RecyclerView.Adapter<DataBoundViewHolder>() {
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
        public int getItemCount() {
            return mUserList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.simple_list_item_1;
        }
    };
}
