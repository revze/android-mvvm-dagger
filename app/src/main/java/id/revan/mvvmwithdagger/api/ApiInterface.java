package id.revan.mvvmwithdagger.api;

import java.util.List;

import id.revan.mvvmwithdagger.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{post_id}")
    Call<Post> getPost(@Path("post_id") String id);
}
