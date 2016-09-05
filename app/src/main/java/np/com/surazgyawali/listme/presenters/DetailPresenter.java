package np.com.surazgyawali.listme.presenters;


import java.util.List;

import np.com.surazgyawali.listme.models.Comment;
import np.com.surazgyawali.listme.models.Post;
import np.com.surazgyawali.listme.services.ForumService;
import np.com.surazgyawali.listme.views.DetailActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by root on 8/28/16.
 */

    public class DetailPresenter {

        DetailActivity mView;
        ForumService mForum;

        public DetailPresenter(DetailActivity activity, ForumService forum) {

            mView = activity;
            mForum = forum;
        }

        public void loadPost() {

            mForum.getApi()
                    .getPost(mView.getPostId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Post>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Post post) {

                            mView.displayPost(post);
                        }
                    });
        }

        public void loadComments() {

            mForum.getApi()
                    .getComments(mView.getPostId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Comment>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<Comment> comments) {

                            mView.displayComments(comments);
                        }
                    });
        }
}
