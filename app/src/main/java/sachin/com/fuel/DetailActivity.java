package sachin.com.fuel;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import sachin.com.fuel.Helper.UserInfo;

public class DetailActivity extends AppCompatActivity {

    String id;
    DatabaseHelper databaseHelper;
    TextView username, password, email, address, phone, gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getStringExtra("id");
        databaseHelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        Log.i("DetailActivity","ID:"+id);

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,RegisterActivity.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();

            }
        });

    }


    public void showConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User!!");
        builder.setMessage("Are You Sure??");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteUser(id);
                finish();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }


    public void exitApplicationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Application!!");
        builder.setMessage("Are You Sure??");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitApplicationDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showDetail();
    }

    public void showDetail(){
        UserInfo info = databaseHelper.getUserInfo(id);
        username.setText(info.username);
        password.setText(info.password);
        email.setText(info.email);
        address.setText(info.address);
        phone.setText(info.phone);
        gender.setText(info.gender);

    }
}


