package id.revan.mvvmwithdagger.ui.postdetail;

import id.revan.mvvmwithdagger.model.Post;

abstract class PostDetailState {
}

class LoadingState extends PostDetailState{

}

class FailedState extends PostDetailState {
    String message;

    public FailedState(String message) {
        this.message = message;
    }
}

class SuccessState extends PostDetailState {
    Post post;

    public SuccessState(Post post) {
        this.post = post;
    }
}
