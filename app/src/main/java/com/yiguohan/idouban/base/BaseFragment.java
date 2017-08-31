package com.yiguohan.idouban.base;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.yiguohan.idouban.R;

/**
 * Created by yiguohan.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment implements IBaseView {

    private Toast mToast;

    public void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hasMultiFragment()) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!hasMultiFragment()) {

        }
    }

    public abstract boolean hasMultiFragment();

    protected abstract String setFragmentName();


    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {
        showProgress("");
    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public void showTheToast(int resId) {
        showTheToast(getString(resId));
    }

    @Override
    public void showTheToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onServerFail(String msg) {

    }

    protected void startActivity(Class<?> pClass){
        Intent intent = new Intent();
        intent.setClass(getActivity(),pClass);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.trans_next_in,R.anim.trans_next_out);
    }

    protected void startActivityByIntent(Intent intent){
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.trans_next_in,R.anim.trans_next_out);
    }
}
