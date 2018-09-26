package com.example.shaochengyang.fashionnova.data;

import com.example.shaochengyang.fashionnova.data.database.IDbHelper;
import com.example.shaochengyang.fashionnova.data.database.model.ShoppingCartObject;
import com.example.shaochengyang.fashionnova.data.network.INetworkHelper;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.data.network.model.OrderHistoryObject;
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

        void passAPIKey(String appapikey, String id, String firstname, String email, String mobile);
    }

    interface onSignUpListener{

        void getSignUpResult(String result);
    }

    interface onCheckOutListener{

        void showResult();
    }

    interface onOrderHistoryListener{

        void passOrderHistory(List<OrderHistoryObject> orderList);
    }

    interface onDatabaseListener{

        void passShoppingCart(List<ShoppingCartObject> scList);

        void passDeletedShoppingCart(List<ShoppingCartObject> scList);

        void passProductInfo(String pid, String pname, String quantity, String prize);
    }

}