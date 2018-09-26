package com.example.shaochengyang.fashionnova.data;

import android.widget.ImageView;

import com.example.shaochengyang.fashionnova.data.database.IDbHelper;
import com.example.shaochengyang.fashionnova.data.database.model.ShoppingCartObject;
import com.example.shaochengyang.fashionnova.data.network.INetworkHelper;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.data.network.model.ProductListObj;
import com.example.shaochengyang.fashionnova.data.network.model.SubCategory;

import java.util.List;

public interface IDataManager extends INetworkHelper, IDbHelper {

    interface OnCollectionListener{
        void passCollection(List<Collection> collectionList);
    }

    interface onSubCategoryListener{

        void passSubCategory(List<SubCategory> subCategoryList);
    }

    interface onProductListListener{

        void passProductList(List<ProductListObj> productList);
    }

    interface onLoginListener{

        void passAPIKey(String appapikey, String id);
    }

    interface onSignUpListener{

        void getSignUpResult(String result);
    }

    interface onDatabaseListener{

        void passShoppingCart(List<ShoppingCartObject> scList);
    }

}