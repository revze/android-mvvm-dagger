package id.revan.mvvmwithdagger.di;

import android.content.Context;

import id.revan.mvvmwithdagger.App;

public class Injector {
    public static AppGraph create(Context context) {
        return DaggerAppComponent.builder().uIModule(new UIModule(context)).build();
    }

    public static AppGraph obtain() {
        return App.get().getInjector();
    }
}
