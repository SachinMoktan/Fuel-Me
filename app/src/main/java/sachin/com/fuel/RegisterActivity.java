package sachin.com.fuel;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import sachin.com.fuel.Helper.UserInfo;


public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email, address, phone;
    RadioGroup gender;
    ImageView image2;
    Button img, register, cancel;

    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        databaseHelper = new DatabaseHelper(this);

        sharedPreferences = getSharedPreferences("Userinfo", MODE_PRIVATE);

        id = getIntent().getIntExtra("id",0);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        image2 = findViewById(R.id.image2);
        img = findViewById(R.id.img);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        if (id != 0) {
            UserInfo info = databaseHelper.getUserInfo(id + "");
            username.setText(info.username);
            password.setText(info.password);
            email.setText(info.email);
            address.setText(info.address);
            phone.setText(info.phone);
            if (info.gender.equals("Male")) {
                ((RadioButton) findViewById(R.id.male)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.female)).setChecked(true);

            }
            register.setText("Update");
        }



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String emailValue = email.getText().toString();
                String addresValue = address.getText().toString();
                String phoneValue = phone.getText().toString();
                RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkedBtn.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", usernameValue);
                editor.putString("password", passwordValue);
                editor.putString("email", emailValue);
                editor.putString("address", addresValue);
                editor.putString("phone", phoneValue);
                editor.putString("gender", genderValue);

                editor.apply();
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", usernameValue);
                contentValues.put("password", passwordValue);
                contentValues.put("email", emailValue);
                contentValues.put("address", addresValue);
                contentValues.put("phone", phoneValue);
                contentValues.put("gender", genderValue);

                if (id==0) {

                    databaseHelper.insertUser(contentValues);

                    Toast.makeText(RegisterActivity.this, "Userinfo saved", Toast.LENGTH_LONG).show();
                }else {
                    databaseHelper.updateUser(id+"",contentValues);
                    Toast.makeText(RegisterActivity.this, "Userinfo updated", Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });




    }
}
