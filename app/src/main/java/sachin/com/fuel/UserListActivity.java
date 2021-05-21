package sachin.com.fuel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import sachin.com.fuel.Helper.UserInfo;

public class UserListActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        databaseHelper = new DatabaseHelper(this);
        container = findViewById(R.id.container);
        displayData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayData();
    }

    public void displayData(){
        ArrayList<UserInfo> list = databaseHelper.getUserList();
        container.removeAllViews();

        for (int i = 0; i < list.size(); i++){
            UserInfo info = list.get(i);
        }

        for (final UserInfo info : list) {

            TextView textView = new TextView(this);
            textView.setText(info.username + "\n" + info.address);
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout,null);
            TextView username = view.findViewById(R.id.name),
                    address = view.findViewById(R.id.address);
            username.setText(info.username);
            address.setText(info.address);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserListActivity.this,DetailActivity.class);
                    intent.putExtra("id",info.id);
                    intent.putExtra("username",info.username);
                    Log.i("UserlistActivity","ID:"+info.id);

                    startActivity(intent);
                }
            });


            container.addView(view);
        }
    }
}
