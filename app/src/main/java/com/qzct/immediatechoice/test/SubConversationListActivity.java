package com.qzct.immediatechoice.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.qzct.immediatechoice.R;

import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.SubConversationListFragment;

/**
 * Created by Bob on 15/11/3.
 * 聚合会话列表
 */
public class SubConversationListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_fragment);
        SubConversationListFragment fragment = new SubConversationListFragment();
        fragment.setAdapter(new SubConversationListAdapterEx(RongContext.getInstance()));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_fragment, fragment);
        transaction.commit();

//        Intent intent = getIntent();
//        if (intent.getData() == null) {
//            return;
//        }
//        //聚合会话参数
//        String type = intent.getData().getQueryParameter("type");
//
//        if (type == null)
//            return;
//
//        if (type.equals("group")) {
//            setTitle(R.string.de_actionbar_sub_group);
//        } else if (type.equals("private")) {
//            setTitle(R.string.de_actionbar_sub_private);
//        } else if (type.equals("discussion")) {
//            setTitle(R.string.de_actionbar_sub_discussion);
//        } else if (type.equals("system")) {
//            setTitle(R.string.de_actionbar_sub_system);
//        } else {
//            setTitle(R.string.de_actionbar_sub_defult);
//        }
    }
}
