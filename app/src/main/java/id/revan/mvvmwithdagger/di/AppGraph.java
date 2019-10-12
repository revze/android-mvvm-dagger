package id.revan.mvvmwithdagger.di;

import id.revan.mvvmwithdagger.ui.main.MainActivity;
import id.revan.mvvmwithdagger.ui.postdetail.PostDetailActivity;

public interface AppGraph {
    void inject(MainActivity activity);

    void inject(PostDetailActivity activity);
}
