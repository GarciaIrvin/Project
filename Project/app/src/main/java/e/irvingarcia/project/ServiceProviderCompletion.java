package e.irvingarcia.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ServiceProviderCompletion extends AppCompatActivity {

    EditText streetBox;
    EditText cityBox;
    EditText postalBox;
    private Users temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();

        setContentView(R.layout.activity_service_provider);
        String users=extras.getString("users");
        String pass=extras.getString("pass");
        MyDBHandler dbHandler=new MyDBHandler(this);
        temp=dbHandler.getProfile(users,pass);

        streetBox=(EditText)findViewById(R.id.editStreetS);
        cityBox=(EditText)findViewById(R.id.editCityS);
        cityBox=(EditText)findViewById(R.id.editPostalS);

        streetBox.setText(temp.getStreet());
        cityBox.setText(temp.getCity());
        postalBox.setText(temp.getPostal());



    }
    public void finish(View view){
        MyDBHandler dbHandler= new MyDBHandler(this);
        dbHandler.completedForm(temp);
        dbHandler.close();
    }

}
