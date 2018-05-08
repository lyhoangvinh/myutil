package lyhoangvinh.com.myutil.animation;

import android.animation.Animator;

public class SimpleAnimatorListener implements Animator.AnimatorListener {

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {
        onAnimationEnd(animator);
    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
