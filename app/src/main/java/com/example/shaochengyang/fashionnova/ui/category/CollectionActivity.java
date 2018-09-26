package com.example.shaochengyang.fashionnova.ui.category;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shaochengyang.fashionnova.R;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.ui.product.ProductActivity;
import com.example.shaochengyang.fashionnova.ui.shoppingcart.ShoppingCartActivity;
import com.example.shaochengyang.fashionnova.ui.subcategory.SubCategoryActivity;

import java.util.List;

public class CollectionActivity extends AppCompatActivity implements ICollectionView {
    private static final String TAG = "CollectionActivity";

    RecyclerView recyclerView;
    Adapter adapter;
    ImageView shoppingcartView,backIcon;

    ICollectionPresenter iCollectionPresenter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        recyclerView = findViewById(R.id.collectionrecyclerview);

        /*String api_key = getIntent().getExtras().getString("apikey");
        String user_id = getIntent().getExtras().getString("userid");*/


        sharedPreferences=getSharedPreferences("mySP",MODE_PRIVATE);
        String api_key =  sharedPreferences.getString("api_key","not found");
        String user_id = sharedPreferences.getString("user_id","not found");
        //Toast.makeText(this, ""+api, Toast.LENGTH_SHORT).show();
        iCollectionPresenter = new CollectionPresenter(this);
        iCollectionPresenter.readCollectionFromServer(api_key,user_id);

        shoppingcartView = findViewById(R.id.shoppingcartView);

        backIcon = findViewById(R.id.icon_back);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        shoppingcartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CollectionActivity.this,ShoppingCartActivity.class);
                startActivity(i);
            }
        });
        //Toast.makeText(this, ""+user_id+" "+api_key, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showCollectionList(List<Collection> collectionList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CollectionListAdaptor(collectionList, CollectionActivity.this);
        recyclerView.setAdapter(adapter);

        /*for(int i = 0 ; i < collectionList.size();i++){
            String cid = collectionList.get(i).getCid();
            Toast.makeText(this, ""+cid, Toast.LENGTH_SHORT).show();
            Collection collection = collectionList.get(i);
            String co = collection.getCdiscription();

        }*/
    }


    /*@Override
    public void onItemClick(View rootView, int position, List<Collection> collectonList) {
        Log.d(TAG, "onItemClick: "+position);
        Intent subCategoryIntent = new Intent(this,SubCategoryActivity.class);
        subCategoryIntent.putExtra("cid",collectonList.get(position).getCid());
        startActivity(subCategoryIntent);
    }*/
}
