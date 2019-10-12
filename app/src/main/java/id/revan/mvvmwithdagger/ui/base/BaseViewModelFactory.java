package id.revan.mvvmwithdagger.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.revan.mvvmwithdagger.api.ApiInterface;
import id.revan.mvvmwithdagger.ui.main.MainVM;
import id.revan.mvvmwithdagger.ui.postdetail.PostDetailVM;

public class BaseViewModelFactory implements ViewModelProvider.Factory {

    private ApiInterface apiInterface;

    public BaseViewModelFactory(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainVM.class)) {
            return (T) new MainVM(apiInterface);
        }
        if (modelClass.isAssignableFrom(PostDetailVM.class)) {
            return (T) new PostDetailVM(apiInterface);
        }
        throw new IllegalArgumentException("Unknown ViewMode Class");
    }
}
