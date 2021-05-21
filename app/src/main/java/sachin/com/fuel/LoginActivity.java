package sachin.com.fuel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    CheckBox rememberme;
    TextView forgot;

    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        sharedPreferences = getSharedPreferences("Userinfo", MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(this);


        Log.i("Lifecycle","onCreate");


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        rememberme = findViewById(R.id.rememberme);
        forgot = findViewById(R.id.forgot);

        if (sharedPreferences.getBoolean("rememberme", false)){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }



            login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regiteredUsername = sharedPreferences.getString("username", "");
                String regiteredPassword = sharedPreferences.getString("password", "");
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                if (databaseHelper.isLoginValid(usernameValue,  passwordValue)){
                    if (rememberme.isChecked()){
                        sharedPreferences.edit().putBoolean("rememberme",true).apply();
                    }
                    Intent intent = new Intent( LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent = new Intent( LoginActivity.this, UserListActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this,"Login Failure", Toast.LENGTH_LONG).show();
                }



            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Btn clicked", Toast.LENGTH_LONG).show();
            }
        });



    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LIfecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LIfecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LIfecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LIfecycle", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LIfecycle", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LIfecycle", "onDestroy");
    }
}
