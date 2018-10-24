package e.irvingarcia.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    TextView welcomeView;
    Users user;
    Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        String users=extras.getString("users");
        setContentView(R.layout.activity_welcome);
        welcomeView=(TextView)findViewById(R.id.firstName);
        welcomeView.setText("WELCOME "+users);
    }
}
