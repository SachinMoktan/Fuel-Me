package sachin.com.fuel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ImageView gps, maps, fuel, routes, calculator, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Userinfo", MODE_PRIVATE);

        gps = findViewById(R.id.gps);
        maps = findViewById(R.id.maps);
        fuel = findViewById(R.id.fuel);
        routes = findViewById(R.id.route);
        calculator = findViewById(R.id.calculator);
        calendar = findViewById(R.id.calendar);




        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsActivity();
            }
        });

        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFuelMapsActivity();
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGpsMapsActivity();
            }
        });

        routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRoutesMapsActivity();
            }
        });

    }

    public void openMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openFuelMapsActivity(){
        Intent intent = new Intent(this, FuelMapsActivity.class);
        startActivity(intent);
    }

    public void openGpsMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openRoutesMapsActivity(){
        Intent intent = new Intent(this, FuelMapsActivity .class);
        startActivity(intent);
    }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.search:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));

                break;

            case R.id.contacts:
                startActivity(new Intent(this, ContactActivity.class));

                break;

            case R.id.developers:
                startActivity(new Intent(this, DevelopersActivity.class));
                break;


            case R.id.logout:
                sharedPreferences.edit().putBoolean("rememberme", false).apply();
                startActivity(new Intent(this, LoginActivity.class));
                finish();

                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
