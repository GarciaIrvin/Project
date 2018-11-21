package e.irvingarcia.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Service_Profile extends AppCompatActivity {
    TextView nameView;
    TextView companyView;
    TextView phoneView;
    TextView descriptionView;
    Users temp;
    ServiceProvider serviceProvider;
    ListView service;
    List<Service> services;
    ServiceList serviceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_profile);
        Bundle extras=getIntent().getExtras();

        String users=extras.getString("users");
        String pass=extras.getString("pass");
        MyDBHandler dbHandler=new MyDBHandler(this);

        temp=dbHandler.getProfile(users,pass);
        serviceProvider=dbHandler.getServiceProvider(dbHandler.getUserID(temp));

        nameView=(TextView)findViewById(R.id.nameP);
        companyView=(TextView)findViewById(R.id.companyP);
        phoneView=(TextView)findViewById(R.id.phoneP);
        descriptionView=(TextView)findViewById(R.id.descripP);

        nameView.setText(temp.getFirst());
        companyView.setText(serviceProvider.getCompany());
        phoneView.setText(serviceProvider.getPhone());
        descriptionView.setText(serviceProvider.getDescription());

        service=(ListView)findViewById(R.id.listViewP);
//        final ArrayList<Service> serviceTemp=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
//        services=serviceTemp;
//        serviceList=new ServiceList(this,services);
//        service.setAdapter(serviceList);
//
//
//        service.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Service serv = services.get(i);
//                showUpdateDeleteDialog(serv);
//                return true;
//            }
//        });
        dbHandler.close();
    }

    @Override
    protected void onStart(){
        super.onStart();
        MyDBHandler dbHandler=new MyDBHandler(this);
        final ArrayList<Service> serviceTemp=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
        services=serviceTemp;
        serviceList=new ServiceList(this,services);
        service.setAdapter(serviceList);

        service.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service serv = services.get(i);
                showUpdateDeleteDialog(serv);
                return true;
            }
        });
        dbHandler.close();
    }

    public void addService (View view){
        MyDBHandler dbHandler=new MyDBHandler(this);
        Intent intent = new Intent(getApplicationContext(), Service_ADD.class);
        intent.putExtra("users", temp.getUser());
        intent.putExtra("pass", temp.getPass());
        startActivityForResult(intent, 0);
        services=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
        serviceList.notifyDataSetChanged();
        dbHandler.close();

    }

    private void showUpdateDeleteDialog(Service serv) {
        MyDBHandler dbHandler=new MyDBHandler(this);
        Service tempService=serv;
        dbHandler.deleteServicefromAccount(dbHandler.getUserID(temp),tempService);
//        services=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
//        serviceList.notifyDataSetChanged();
        onStart();
        dbHandler.close();

        //dbHandler.addServicetoAccount(dbHandler.getUserID(user),tempService);
        //Toast.makeText(Service_ADD.this, "Added Successfully", Toast.LENGTH_LONG).show();
    }


}
