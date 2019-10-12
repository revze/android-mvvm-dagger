package id.revan.mvvmwithdagger.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import id.revan.mvvmwithdagger.R;
import id.revan.mvvmwithdagger.adapter.PostAdapter;
import id.revan.mvvmwithdagger.api.ApiInterface;
import id.revan.mvvmwithdagger.di.Injector;
import id.revan.mvvmwithdagger.helper.LayoutHelper;
import id.revan.mvvmwithdagger.model.Post;
import id.revan.mvvmwithdagger.ui.base.BaseViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    ApiInterface apiInterface;

    @Inject
    LayoutHelper layoutHelper;

    private List<Post> posts = new ArrayList<>();
    private PostAdapter adapter;
    private RecyclerView rvPost;
    private LinearLayout layoutWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Injector.obtain().inject(this);

        rvPost = findViewById(R.id.rv_post);
        adapter = new PostAdapter(posts);
        rvPost.setAdapter(adapter);
        rvPost.setLayoutManager(new LinearLayoutManager(this));

        layoutWrapper = findViewById(R.id.layout_wrapper);

        MainVM mainVM = ViewModelProviders.of(this, new BaseViewModelFactory(apiInterface)).get(MainVM.class);
        mainVM.getPostsState().observe(this, mainState);

        mainVM.getPosts();
    }

    private Observer<MainState> mainState = new Observer<MainState>() {
        @Override
        public void onChanged(MainState mainState) {
            if (mainState instanceof SuccessState) {
                layoutWrapper.setVisibility(View.GONE);
                rvPost.setVisibility(View.VISIBLE);
                posts.clear();
                posts.addAll(((SuccessState) mainState).posts);
                adapter.notifyDataSetChanged();
            } else if (mainState instanceof FailedState) {
                layoutWrapper.setVisibility(View.VISIBLE);
                rvPost.setVisibility(View.GONE);
                layoutHelper.showLayoutError(layoutWrapper, ((FailedState) mainState).message);
            } else {
                layoutWrapper.setVisibility(View.VISIBLE);
                rvPost.setVisibility(View.GONE);
                layoutHelper.showLoader(layoutWrapper);
            }
        }
    };
}
