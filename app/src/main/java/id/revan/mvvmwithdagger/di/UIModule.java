package id.revan.mvvmwithdagger.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import id.revan.mvvmwithdagger.helper.LayoutHelper;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    LayoutHelper provideLayoutHelper() {
        return new LayoutHelper(context);
    }
}
