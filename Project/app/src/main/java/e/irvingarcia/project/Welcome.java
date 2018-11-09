package e.irvingarcia.project;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        String role=extras.getString("role");
        setContentView(R.layout.activity_welcome);
        welcomeView=(TextView)findViewById(R.id.firstName);
        welcomeView.setText("WELCOME "+users+" you logged in as "+ role);
    }
}
