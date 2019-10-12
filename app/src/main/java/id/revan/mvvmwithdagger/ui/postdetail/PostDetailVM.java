package id.revan.mvvmwithdagger.ui.postdetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.revan.mvvmwithdagger.api.ApiInterface;
import id.revan.mvvmwithdagger.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailVM extends ViewModel {
    private MutableLiveData<PostDetailState> state = new MutableLiveData<>();
    private ApiInterface apiInterface;

    public PostDetailVM(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public MutableLiveData<PostDetailState> getState() {
        return state;
    }

    void getPost(String id) {
        state.setValue(new LoadingState());

        apiInterface.getPost(id).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                state.setValue(new SuccessState(response.body()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                state.setValue(new FailedState("Failed to load post"));
            }
        });
    }
}
