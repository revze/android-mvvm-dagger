package id.revan.mvvmwithdagger.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.revan.mvvmwithdagger.api.ApiInterface;
import id.revan.mvvmwithdagger.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainVM extends ViewModel {
    private ApiInterface apiInterface;
    private MutableLiveData<MainState> postsState = new MutableLiveData<>();

    public MainVM(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public MutableLiveData<MainState> getPostsState() {
        return postsState;
    }

    void getPosts() {
        postsState.setValue(new LoadingState());

        apiInterface.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postsState.setValue(new SuccessState(response.body()));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postsState.setValue(new FailedState("Failed to load post"));
            }
        });
    }
}
