package com.example.shaochengyang.fashionnova.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shaochengyang.fashionnova.R;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.ui.category.CollectionActivity;
import com.example.shaochengyang.fashionnova.ui.category.CollectionListAdaptor;
import com.example.shaochengyang.fashionnova.ui.category.CollectionPresenter;
import com.example.shaochengyang.fashionnova.ui.category.ICollectionPresenter;
import com.example.shaochengyang.fashionnova.ui.category.ICollectionView;
import com.example.shaochengyang.fashionnova.ui.login.LoginActivity;
import com.example.shaochengyang.fashionnova.ui.myorder.MyOrderActivity;
import com.example.shaochengyang.fashionnova.ui.shoppingcart.ShoppingCartActivity;
import com.example.shaochengyang.fashionnova.ui.topseller.TopSellerActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener/*,ICollectionView*/ {
    /*RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ICollectionPresenter iCollectionPresenter;
    SharedPreferences sharedPreferences;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*recyclerView = findViewById(R.id.collectionrecyclerview);

        sharedPreferences=getSharedPreferences("mySP",MODE_PRIVATE);
        String api_key =  sharedPreferences.getString("api_key","not found");
        String user_id = sharedPreferences.getString("user_id","not found");
        //Toast.makeText(this, ""+api, Toast.LENGTH_SHORT).show();
        iCollectionPresenter = new CollectionPresenter(this);
        iCollectionPresenter.readCollectionFromServer(api_key,user_id);*/



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category, menu);
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
            Intent i = new Intent(this,ShoppingCartActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else */
        if (id == R.id.collection) {
            Intent i = new Intent(MainActivity.this,CollectionActivity.class);
            startActivity(i);

        } else if (id == R.id.shoppingcart) {
            Intent i = new Intent(MainActivity.this,ShoppingCartActivity.class);
            startActivity(i);

        } else if (id == R.id.myorder) {
            Intent i = new Intent(MainActivity.this, MyOrderActivity.class);
            startActivity(i);

        } else if (id == R.id.myfavorites) {
            Intent i = new Intent(this, TopSellerActivity.class);
            startActivity(i);

        } else if (id == R.id.login) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void showCollectionList(List<Collection> collectionList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CollectionListAdaptor(collectionList, MainActivity.this);
        recyclerView.setAdapter(adapter);

    }*/
}
