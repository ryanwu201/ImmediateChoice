package com.qzct.immediatechoice.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.qzct.immediatechoice.R;
import com.qzct.immediatechoice.SettingActivity;
import com.qzct.immediatechoice.adpter.UserAdpter;
import com.qzct.immediatechoice.domain.User;
import com.qzct.immediatechoice.domain.conversation;
import com.qzct.immediatechoice.util.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends baseFragment {

    private View v;
    List<conversation> conversationlist;


    @Override
    public View initview(LayoutInflater inflater, ViewGroup container) {
        v = v.inflate(context, R.layout.fragment_user, null);
////	ll = new LinearLayout(context);
////	lv = new ListView(context);
//		ll.addView(lv);


//		TextView tv = new TextView(context);
//		tv.setText("交流");

        return v;
    }

    @Override
    public void initdata() {

        User user = myApplication.getUser();
        TextView tv_username = (TextView) v.findViewById(R.id.user_tv_username);
//        CircleImageView user_portrait = (CircleImageView)v.findViewById(R.id.user_portrait);
        SmartImageView  user_portrait = (SmartImageView)v.findViewById(R.id.user_portrait);
//        user_portrait.setImageUrl(user.getPortrait_path());
        ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
        x.image().bind(user_portrait,user.getPortrait_path(),options);
        tv_username.setText(user.getUsername());
        final GridView lv = (GridView) v.findViewById(R.id.gv_user);
        ShowConversationFromJsonArrayTask ShowConversationFromJsonArrayTask = new ShowConversationFromJsonArrayTask(context, lv, getString(R.string.url_user));
        ShowConversationFromJsonArrayTask.execute();
        ImageView bt_setting = (ImageView) v.findViewById(R.id.bt_setting);
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
            }
        });
    }


    class ShowConversationFromJsonArrayTask extends AsyncTask<String, String, JSONArray> {

        String spec;
        Context context;
        GridView listView;

        public ShowConversationFromJsonArrayTask(Context context, GridView listView, String spec) {
            this.context = context;
            this.listView = listView;
            this.spec = spec;
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            //返回获取的jasonArray
            return utils.GetJsonArray(spec);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            ArrayList<conversation> conversationlist = null;
            try {
                //new一个info数组
                conversationlist = new ArrayList<conversation>();
                System.out.println("for前");
                //遍历传入的jsonArray
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject temp = jsonArray.getJSONObject(i);
                    //读取相应内容
                    String content = temp.getString("content");
                    String portraiturl = temp.getString("portraiturl");
                    String addresser = temp.getString("addresser");
                    String addressee = temp.getString("addressee");

                    conversation conversation = new conversation(content, portraiturl, addressee, addresser);
                    System.out.println(conversation.toString());
                    conversationlist.add(conversation);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("空1", context.toString());

            Log.i("空2", listView.toString());
            //设置适配器
            listView.setAdapter(new UserAdpter(context, conversationlist));
        }
    }

//	Handler handler =  new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//				System.out.println("连接成功");
//				JSONArray jsonArray =(JSONArray) msg.obj;
//				//显示
//				show(jsonArray);
//		}
//
//		private void show(JSONArray jsonArray) {
//
//			// TODO Auto-generated method stub
//				//通过资源id拿到listview对象
//			 GridView lv =(GridView) v.findViewById(R.id.lv_chat);
//				try {
//					//new一个info数组
//					conversationlist = new ArrayList<conversation>();
//					System.out.println("for前");
//					//遍历传入的jsonArray
//					for(int i=0;i<jsonArray.length();i++){
//						JSONObject temp = jsonArray.getJSONObject(i);
//						//读取相应内容
//						String content = temp.getString("content");
//						String portraiturl = temp.getString("portraiturl");
//						String addresser = temp.getString("addresser");
//						String addressee = temp.getString("addressee");
//
//						conversation conversation = new conversation(content, portraiturl, addressee, addresser);
//						System.out.println(conversation.toString());
//						conversationlist.add(conversation);
//
//					}
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				//设置适配器
//				lv.setAdapter(new UserAdpter(context,conversationlist));
//		}
//	};
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		//拿到json
//		GetJsonarray(getString(R.string.url_user));
//	}
//
//	private void GetJsonarray(final String spec) {
//		//开一个子线程
//		new Thread(){
//			 Message  msg = Message.obtain();
//			public void run() {
//				JSONArray Array = utils.GetJsonArray(spec);
//				msg.obj = Array;
//				handler.sendMessage(msg);
//			};
//		}.start();
//	}


}



	