package com.example.shaochengyang.fashionnova.data;

import android.content.Context;

import com.example.shaochengyang.fashionnova.data.database.DbHelper;
import com.example.shaochengyang.fashionnova.data.database.IDbHelper;
import com.example.shaochengyang.fashionnova.data.network.INetworkHelper;
import com.example.shaochengyang.fashionnova.data.network.NetworkHelper;

public class DataManager implements IDataManager {
    INetworkHelper networkHelper;
    IDbHelper dbHelper;

    public DataManager(Context context) {
        //this is a problem that need to pass the collectionActivity
        networkHelper = new NetworkHelper();
        dbHelper = new DbHelper(context);
    }

    @Override
    public void getCollection(OnCollectionListener onCollectionListener, String api_key, String user_id) {
        networkHelper.getCollection(onCollectionListener, api_key, user_id);
    }

    @Override
    public void getSubCategory(onSubCategoryListener onSubCategoryListener, String cid, String api_key, String user_id) {
        networkHelper.getSubCategory(onSubCategoryListener, cid, api_key, user_id);
    }

    @Override
    public void getProductList(onProductListListener onProductListListener, String cid, String scid, String api_key, String user_id) {
        networkHelper.getProductList(onProductListListener, cid, scid, api_key, user_id);
    }

    @Override
    public void getAPIKey(onLoginListener onLoginListener, String phone, String password) {
        networkHelper.getAPIKey(onLoginListener, phone, password);
    }

    @Override
    public void signUp(onSignUpListener onSignUpListener, String fname, String lname, String address, String password, String email, String mobile) {
        networkHelper.signUp(onSignUpListener,fname,lname,address,password,email,mobile);
    }


    @Override
    public void createRow(IDataManager.onDatabaseListener onDatabaseListener, String pid, String pname, String userQuantity, String prize) {
        dbHelper.createRow(onDatabaseListener,pid,pname,userQuantity,prize);
    }

    @Override
    public void readALL(onDatabaseListener onDatabaseListener) {
        dbHelper.readALL(onDatabaseListener);
    }
}
