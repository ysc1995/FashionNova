package com.example.shaochengyang.fashionnova.data.network;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.shaochengyang.fashionnova.AppController;
import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.data.network.model.ProductListObj;
import com.example.shaochengyang.fashionnova.data.network.model.SubCategory;
import com.example.shaochengyang.fashionnova.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NetworkHelper implements INetworkHelper {

    List<Collection> collectionList ;
    List<SubCategory> subCategoryList;
    List<ProductListObj> productList;
    private static final String TAG = "NetworkHelper";
    //Todo create a static url in a constant class
    //private String urlCollection = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=5ec23161979dd69909960de49e6db800&user_id=1384";
    //private String urlSubCategory = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=107&api_key=dad3b9d91e34282e4d4a9a3309e04441&user_id=1384";
    private String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php";

    @Override
    public void getCollection(final IDataManager.OnCollectionListener onCollectionListener, final String api_key, final String user_id) {
        // perform the volley n/w call

        collectionList = new ArrayList<Collection>();
        /*String api = api_key;
        String id = user_id;
        Log.d(TAG, "getCollection: "+api+id);*/
        String URL = Constant.URL_COLLECTION+"?api_key="+api_key+"&user_id="+user_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("category");

                    for (int i = 0 ; i < jsonArray.length(); i ++){
                        JSONObject category = jsonArray.getJSONObject(i);
                        String cid = category.getString("cid");
                        String cname = category.getString("cname");
                        String cdiscription = category.getString("cdiscription");
                        String cimagerl = category.getString("cimagerl");

                        //Log.d(TAG, "onResponse: "+cid+cname+cdiscription+cimagerl);

                        Collection collection = new Collection(cid, cname, cdiscription, cimagerl);
                        collectionList.add(collection);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onCollectionListener.passCollection(collectionList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void getSubCategory(final IDataManager.onSubCategoryListener onSubCategoryListener, String cid, String api_key, String user_id) {

        subCategoryList = new ArrayList<SubCategory>();
        String URL = Constant.URL_SUBCATEGORY+"?Id="+cid+"&api_key="+api_key+"&user_id="+user_id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL , null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: "+response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("subcategory");
                    for (int i = 0 ; i < jsonArray.length(); i ++){
                        JSONObject subCategory = jsonArray.getJSONObject(i);
                        String scid = subCategory.getString("scid");
                        String scname = subCategory.getString("scname");
                        String scdiscription = subCategory.getString("scdiscription");
                        String scimageurl = subCategory.getString("scimageurl");

                        SubCategory subCategory1 = new SubCategory(scid,scname,scdiscription,scimageurl);
                        subCategoryList.add(subCategory1);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                onSubCategoryListener.passSubCategory(subCategoryList);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        ;
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void getProductList(final IDataManager.onProductListListener onProductListListener, String cid, String scid, String api_key, String user_id) {

        String URL = Constant.URL_PRODUCTLIST+"?cid="+cid+"&scid="+scid+"&api_key="+api_key+"&user_id="+user_id;
        productList = new ArrayList<ProductListObj>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("products");
                    for (int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject productlistobj = jsonArray.getJSONObject(i);
                        String id = productlistobj.getString("id");
                        String pname = productlistobj.getString("pname");
                        String quantity = productlistobj.getString("quantity");
                        String prize = productlistobj.getString("prize");
                        String discription = productlistobj.getString("discription");
                        String image = productlistobj.getString("image");

                        ProductListObj productListObj = new ProductListObj(id,pname,quantity,prize,discription,image);
                        productList.add(productListObj);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onProductListListener.passProductList(productList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void getAPIKey(final IDataManager.onLoginListener onLoginListener, String phone, String password) {

        String LoginUrl = Constant.URL_LOGIN+"?mobile="+phone+"&password="+password;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LoginUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject jsonObject = response.getJSONObject(0);

                    String appapikey = jsonObject.getString("appapikey ");
                    String id = jsonObject.getString("id");

                    onLoginListener.passAPIKey(appapikey,id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void signUp(final IDataManager.onSignUpListener onSignUpListener, String fname, String lname, String address, String password, String email, String mobile) {
        String SignUpUrl = Constant.URL_SIGNUP+"?fname="+fname+"&lname="+lname+"&address="+address+"&email="+email+"&mobile="+mobile+"&password="+password;
        StringRequest stringRequest = new StringRequest(SignUpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onSignUpListener.getSignUpResult(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


}
