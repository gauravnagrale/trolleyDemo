package com.example.newtrolleymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    public String value;
    private ListView listView;
    private Firebase mref;
    public String quest;
    public TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        mref = new Firebase("https://demotrolley.firebaseio.com/Variable/weight/");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mref = database.getReference("Variable");

        mref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                //Map <String, String> map = (Map)dataSnapshot.getValue();
                //quest = map.get("Variable");
                Log.i("xxxxx",dataSnapshot.toString());
                value=String.valueOf(dataSnapshot.getValue());            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupUIViews();

        setupListView();

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
                )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @SuppressWarnings("StatementWithEmptyBody")


    private void setupUIViews(){

        listView = (ListView)findViewById(R.id.lvMain);
    }



    private void setupListView(){

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        break;
                    }
                }
            }
        });
    }

    public void redirect(View view)//clicking and going to next page
    {
        Intent intent = new Intent(getApplicationContext(),Rice.class);
        intent.putExtra("item","rice");
        startActivity(intent);
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;
        private Button button;


        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);
            }

            title = (TextView)convertView.findViewById(R.id.tvMain);
            description = (TextView)convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView)convertView.findViewById(R.id.ivMain);
            button = (Button)convertView.findViewById(R.id.NewButton);


            title.setText(titleArray[position]);
            description.setText(value);
            //button.setTag(position);

            if(titleArray[position].equalsIgnoreCase("Rice")){
                imageView.setImageResource(R.drawable.ic_header_launcher);
                description.setText(value);
                button.setTag(position);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW, Uri.parse("https://www.bigbasket.com/ps/?q=rice"));
                        startActivity(browserIntent);
                    }
                });
            }/**0else if(titleArray[position].equalsIgnoreCase("Sugar")){
             imageView.setImageResource(R.drawable.ic_header_launcher);
             button.setTag(position);
             button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
             Intent browserIntent = new Intent(
             Intent.ACTION_VIEW, Uri.parse("https://www.bigbasket.com/ps/?q=sugar"));
             startActivity(browserIntent);
             }
             });
             }else if(titleArray[position].equalsIgnoreCase("Lentils")){
             imageView.setImageResource(R.drawable.ic_header_launcher);
             button.setTag(position);
             button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
             Intent browserIntent = new Intent(
             Intent.ACTION_VIEW, Uri.parse("https://www.bigbasket.com/ps/?q=lentils"));
             startActivity(browserIntent);
             }
             });
             }**/
            else{
                imageView.setImageResource(R.drawable.ic_header_launcher);
                button.setTag(position);
            }

            return convertView;

        }
    }

}
