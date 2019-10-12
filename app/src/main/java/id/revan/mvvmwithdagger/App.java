package id.revan.mvvmwithdagger;

import android.app.Application;

import id.revan.mvvmwithdagger.di.AppGraph;
import id.revan.mvvmwithdagger.di.Injector;

public class App extends Application {
    private static App app;
    private AppGraph appGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appGraph = Injector.create(this);
    }

    public static App get() {
        return app;
    }

    public AppGraph getInjector() {
        return appGraph;
    }
}
