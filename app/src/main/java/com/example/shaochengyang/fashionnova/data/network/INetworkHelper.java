package com.example.shaochengyang.fashionnova.data.network;


import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionPresenter;
import com.example.shaochengyang.fashionnova.ui.subcategory.SubCategoryPresenter;

public interface INetworkHelper {
    void getCollection(IDataManager.OnCollectionListener onCollectionListener);


    void getSubCategory(IDataManager.onSubCategoryListener onSubCategoryListener);
}
