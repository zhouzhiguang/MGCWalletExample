package example.mgcoin.com.mgcwalletexample.framework.activities;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;


@SuppressLint("NewApi")
public class BaseActivity extends RxAppCompatActivity {
    public static final String KEY_UUID = "uuid";

    private boolean mIsDestroyed = false;
    private static Handler handler;
    //location
    // public static final int REQUEST_CODE_CALL = 300;
    //
    private Toolbar toolbar;

    public String registeredsucceed = "0";
    public int LoginOut = 100000;


    private CompositeDisposable compositeDisposable;
    public Observer mReuseSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


    }


    @Override
    public final void setContentView(int i) {
        super.setContentView(i);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);

        setIntent(intent);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected Observer getReuseSubscriber() {
        return mReuseSubscriber;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        mIsDestroyed = true;
        super.onDestroy();
        // manual listen event should release by yourself.
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        //auto release register with Annotation RxSubscribe.
        // RxBus.getInstance().unregister(this);
    }

    @Override
    public final boolean isDestroyed() {
        // TODO Auto-generated method stub
        return mIsDestroyed;
    }

    protected final void callbackForResult(int resultCode) {
        Intent data = new Intent();

        callbackForResult(resultCode, data);
    }

    protected final void callbackForResult(int resultCode, Intent data) {
        data.putExtra(KEY_UUID, getIntent().getStringExtra(KEY_UUID));

        setResult(resultCode, data);
    }

    protected int getLayoutId() {
        return 0;
    }

    protected boolean canSwipeBack() {
        return true;
    }


    protected void addFragment(int containerId, Fragment fragment) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.add(containerId, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected void replaceFragment(int containerId, Fragment fragment) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(containerId, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * 延时弹出键盘
     *
     * @param focus 键盘的焦点项
     */
    protected void showKeyboardDelayed(View focus) {
        final View viewToFocus = focus;
        if (focus != null) {
            focus.requestFocus();
        }

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(true);
                }
            }
        }, 200);
    }

    protected final Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper());
        }
        return handler;
    }

    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


}
