package com.example.shaochengyang.fashionnova.data;

import android.content.Context;

import com.example.shaochengyang.fashionnova.data.network.INetworkHelper;
import com.example.shaochengyang.fashionnova.data.network.NetworkHelper;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionActivity;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionPresenter;
import com.example.shaochengyang.fashionnova.ui.subcategory.SubCategoryPresenter;

public class DataManager implements IDataManager {
    INetworkHelper networkHelper;

    public DataManager(Context collectionActivity) {
        //this is a problem that need to pass the collectionActivity
        networkHelper = new NetworkHelper();

    }

    @Override
    public void getCollection(OnCollectionListener onCollectionListener) {
        networkHelper.getCollection(onCollectionListener);
    }

    @Override
    public void getSubCategory(onSubCategoryListener onSubCategoryListener) {
        networkHelper.getSubCategory(onSubCategoryListener);
    }


}
