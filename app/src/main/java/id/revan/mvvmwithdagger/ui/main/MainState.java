package id.revan.mvvmwithdagger.ui.main;

import java.util.List;

import id.revan.mvvmwithdagger.model.Post;

abstract class MainState {
}

class LoadingState extends MainState {

}

class FailedState extends MainState {
    String message;

    public FailedState(String message) {
        this.message = message;
    }
}

class SuccessState extends MainState {
    List<Post> posts;

    public SuccessState(List<Post> posts) {
        this.posts = posts;
    }
}
