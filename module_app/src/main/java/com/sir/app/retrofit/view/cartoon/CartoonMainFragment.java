package com.sir.app.retrofit.view.cartoon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.view.cartoon.fragment.BloodFragment;
import com.sir.app.retrofit.view.cartoon.fragment.ChoiceFragment;
import com.sir.app.retrofit.view.cartoon.fragment.DomesticFragment;
import com.sir.app.retrofit.view.widget.floating.FloatingActionButton;
import com.sir.app.retrofit.view.widget.floating.FloatingActionsMenu;
import com.space.app.base.BaseFragmentV4;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 动漫板块主界面
 * Created by zhuyinan on 2017/4/1.
 */

public class CartoonMainFragment extends BaseFragmentV4 implements View.OnClickListener {

    List<Fragment> mFragments;
    int upPosition = 0;
    FloatingActionButton upButton;
    @Bind(R.id.actions_menu)
    FloatingActionsMenu actionsMenu;

    @Override
    public int bindLayout() {
        return R.layout.fragment_cartoon_main;
    }

    @Override
    public void initView(View view) {
        mFragments = new ArrayList<>();
        mFragments.add(new ChoiceFragment());
        mFragments.add(new BloodFragment());
        mFragments.add(new DomesticFragment());
        upButton = (FloatingActionButton) findViewById(R.id.action_a);
        upButton.setSize(FloatingActionButton.SIZE_NORMAL);
        findViewById(R.id.action_a).setOnClickListener(this);
        findViewById(R.id.action_b).setOnClickListener(this);
        findViewById(R.id.action_c).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        setShowFragment(R.id.main_cartoon, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_a:
                actionsMenu.collapse();
                setActionBtn(view, 0);
                setShowFragment(R.id.main_cartoon, 0);
                break;
            case R.id.action_b:
                actionsMenu.collapse();
                setActionBtn(view, 1);
                setShowFragment(R.id.main_cartoon, 1);
                break;
            case R.id.action_c:
                setActionBtn(view, 2);
                setShowFragment(R.id.main_cartoon, 2);
                break;
        }
    }


    private void setActionBtn(View view, int position) {
        if (upPosition != position) {
            upButton.setSize(FloatingActionButton.SIZE_MINI);
            FloatingActionButton button = (FloatingActionButton) view;
            button.setSize(FloatingActionButton.SIZE_NORMAL);
            upButton = button;
        }
    }


    protected void setShowFragment(int layoutId, int position) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!mFragments.get(position).isAdded()) {
            transaction.add(layoutId, mFragments.get(position));
        }
        transaction.show(mFragments.get(position));
        if (upPosition != position) {
            transaction.hide(mFragments.get(upPosition));
        }
        transaction.commit();
        upPosition = position;
    }
}
