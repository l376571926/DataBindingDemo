package group.tonight.databingdemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class DataBoundViewHolder extends RecyclerView.ViewHolder {

    static DataBoundViewHolder create(LayoutInflater inflater, @NonNull ViewGroup parent, int layoutId) {
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        return new DataBoundViewHolder(inflate);
    }

    private ViewDataBinding mBinding;

    public DataBoundViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }

    public void bindTo(Object object) {
        mBinding.setVariable(BR.data, object);
        mBinding.executePendingBindings();
    }

}