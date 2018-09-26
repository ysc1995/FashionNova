package com.example.shaochengyang.fashionnova.data.network;


import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.ui.product.ProductPresenter;
import com.example.shaochengyang.fashionnova.ui.signup.SignUpPresenter;

public interface INetworkHelper {
    void getCollection(IDataManager.OnCollectionListener onCollectionListener, String api_key, String user_id);


    void getSubCategory(IDataManager.onSubCategoryListener onSubCategoryListener, String cid, String api_key, String user_id);


    void getProductList(IDataManager.onProductListListener onProductListListener, String cid, String scid, String api_key, String user_id);


    void getAPIKey(IDataManager.onLoginListener onLoginListener, String phone, String password);

    void signUp(IDataManager.onSignUpListener onSignUpListener, String fname, String lname, String address, String password, String email, String mobile);

}
