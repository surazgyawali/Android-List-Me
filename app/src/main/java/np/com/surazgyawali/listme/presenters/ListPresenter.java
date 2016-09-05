package np.com.surazgyawali.listme.presenters;

import java.util.List;

import np.com.surazgyawali.listme.models.Post;
import np.com.surazgyawali.listme.services.ForumService;
import np.com.surazgyawali.listme.views.ListActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 8/28/16.
 */
public class ListPresenter {
    ListActivity mView;
    ForumService mForum;

    public ListPresenter(ListActivity view, ForumService forum) {

        mView = view;
        mForum = forum;
    }

    public void loadPosts() {

        mForum.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                        mView.displayPosts(posts);
                    }
                });
    }
}
