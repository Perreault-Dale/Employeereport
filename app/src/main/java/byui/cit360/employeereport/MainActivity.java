package byui.cit360.employeereport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.TextView;

import byui.cit360.employeereport.control.Weather;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    TextView weatherLabel;
    Weather w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Runnable task1 = () -> {
            weatherLabel = findViewById(R.id.textView);
            w = new Weather();
            w.execute();
            weatherLabel.setText(w.getWeatherString());
        };
        Thread t1 = new Thread(task1);
        t1.start();

        View team_view = findViewById(R.id.buttonTeam);
        team_view.setOnClickListener(this);

        View empl_view = findViewById(R.id.buttonEmpl);
        empl_view.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonTeam) {
            Intent intent = new Intent(this,TeamActivity.class);
            this.startActivity(intent);
        } else if (view.getId() == R.id.buttonEmpl) {
            Intent intent = new Intent(this,EmployeeActivity.class);
            this.startActivity(intent);
        }
    }
}
