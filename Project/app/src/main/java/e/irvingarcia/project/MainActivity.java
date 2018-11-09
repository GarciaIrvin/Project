package e.irvingarcia.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText userBox;
    EditText passBox;
    TextView viewStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userBox=(EditText) findViewById(R.id.editUserLogin);
        passBox=(EditText) findViewById(R.id.editPassLogin);
        viewStatus=(TextView) findViewById(R.id.status);

        MyDBHandler dbHandler=new MyDBHandler(this);
        boolean flag=dbHandler.findUser("admin","admin");

        if(!flag) {
            Admin admin = new Admin("admin", "admin", "Admin");

            // TODO: add to database
            dbHandler.addAdmin(admin);
            //dbHandler.close();
        }
        dbHandler.close();
    }
    public void createUser(View view){
        Intent intent= new Intent(getApplicationContext(),UserCreate.class);
        startActivityForResult(intent,0);
    }

    public void lookupUser (View view) {

        // TODO: get from Database
        MyDBHandler dbHandler=new MyDBHandler(this);
        boolean flag=dbHandler.findUser(userBox.getText().toString(),passBox.getText().toString());

        if (flag==true) {
            viewStatus.setText("Nice");
            Users temp=dbHandler.getProfile(userBox.getText().toString(),passBox.getText().toString());
            if(temp.getRole().equals("Admin")) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                intent.putExtra("users", temp.getFirst());
                intent.putExtra("role", temp.getRole());
                startActivityForResult(intent, 0);
            }
        } else {
            viewStatus.setText("No Match Found");
        }
    }

}
