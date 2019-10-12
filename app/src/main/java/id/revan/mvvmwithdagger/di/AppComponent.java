package id.revan.mvvmwithdagger.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, UIModule.class})
public interface AppComponent extends AppGraph {
}
