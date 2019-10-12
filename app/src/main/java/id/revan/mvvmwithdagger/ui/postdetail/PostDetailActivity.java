package id.revan.mvvmwithdagger.ui.postdetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import id.revan.mvvmwithdagger.R;
import id.revan.mvvmwithdagger.api.ApiInterface;
import id.revan.mvvmwithdagger.di.Injector;
import id.revan.mvvmwithdagger.helper.LayoutHelper;
import id.revan.mvvmwithdagger.model.Post;
import id.revan.mvvmwithdagger.ui.base.BaseViewModelFactory;

public class PostDetailActivity extends AppCompatActivity {

    @Inject
    ApiInterface apiInterface;

    @Inject
    LayoutHelper layoutHelper;

    private LinearLayout layoutWrapper;
    private ScrollView svPostDetail;
    private TextView tvTitle;
    private TextView tvBody;
    public static final String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        String id = getIntent().getStringExtra(ID);

        Injector.obtain().inject(this);

        PostDetailVM vm = ViewModelProviders.of(this, new BaseViewModelFactory(apiInterface)).get(PostDetailVM.class);
        vm.getState().observe(this, observer);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        layoutWrapper = findViewById(R.id.layout_wrapper);
        svPostDetail = findViewById(R.id.sv_post_detail);
        tvTitle = findViewById(R.id.tv_title);
        tvBody = findViewById(R.id.tv_body);

        vm.getPost(id);
    }

    private Observer<PostDetailState> observer = new Observer<PostDetailState>() {
        @Override
        public void onChanged(PostDetailState postDetailState) {
            if (postDetailState instanceof SuccessState) {
                Post post = ((SuccessState) postDetailState).post;

                layoutWrapper.setVisibility(View.GONE);
                svPostDetail.setVisibility(View.VISIBLE);
                tvTitle.setText(post.getTitle());
                tvBody.setText(post.getBody());
            } else if (postDetailState instanceof FailedState) {
                layoutWrapper.setVisibility(View.VISIBLE);
                svPostDetail.setVisibility(View.GONE);
                layoutHelper.showLayoutError(layoutWrapper, ((FailedState) postDetailState).message);
            } else {
                layoutWrapper.setVisibility(View.VISIBLE);
                svPostDetail.setVisibility(View.GONE);
                layoutHelper.showLoader(layoutWrapper);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
