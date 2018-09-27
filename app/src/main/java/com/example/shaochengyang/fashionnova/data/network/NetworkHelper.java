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
import com.example.shaochengyang.fashionnova.data.network.model.OrderHistoryObject;
import com.example.shaochengyang.fashionnova.data.network.model.ProductListObj;
import com.example.shaochengyang.fashionnova.data.network.model.SubCategory;
import com.example.shaochengyang.fashionnova.di.CollectionModule;
import com.example.shaochengyang.fashionnova.di.DaggerMyComponent;
import com.example.shaochengyang.fashionnova.di.MyComponent;
import com.example.shaochengyang.fashionnova.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class NetworkHelper implements INetworkHelper {
    @Inject
    Collection collection;

    MyComponent component;



    List<OrderHistoryObject> orderList;
    List<Collection> collectionList ;
    List<SubCategory> subCategoryList;
    List<ProductListObj> productList;
    private static final String TAG = "NetworkHelper";
    //Todo create a static url in a constant class
    //private String urlCollection = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=5ec23161979dd69909960de49e6db800&user_id=1384";
    //private String urlSubCategory = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=107&api_key=dad3b9d91e34282e4d4a9a3309e04441&user_id=1384";
    private String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php";


   /* public NetworkHelper(){
        component = DaggerMyComponent
                .builder()
                .collectionModule(new CollectionModule())
                .build();
        component.inject(this);
    }
*/
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

                        /*Collection collection = new Collection(cid, cname, cdiscription, cimagerl);
                        collectionList.add(collection);*/

                        component = DaggerMyComponent
                                .builder()
                                .collectionModule(new CollectionModule())
                                .build();
                        component.inject(NetworkHelper.this);


                        collection.setCdiscription(cdiscription);
                        collection.setCid(cid);
                        collection.setCname(cname);
                        collection.setCimagerl(cimagerl);

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
                error.printStackTrace();
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
                    String firstname = jsonObject.getString("firstname");
                    String email = jsonObject.getString("email");
                    String mobile = jsonObject.getString("mobile");
                    String id = jsonObject.getString("id");

                    onLoginListener.passAPIKey(appapikey,id,firstname,email,mobile);
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

    @Override
    public void checkout(final IDataManager.onCheckOutListener onCheckOutListener, String pid, String pname, String quantity, String prize, String api_key, String user_id, String phone, String firstname, String email, String billingadd, String deliveryadd) {

        String URL = Constant.URL_CHECKOUT + "?&item_id="+pid+"&item_names="+pname+"&item_quantity="+quantity+"&final_price="+prize+"&&api_key="+api_key+"&user_id="+user_id+"&user_name="+firstname+"&billingadd="+billingadd+"&deliveryadd="+deliveryadd+"&mobile="+phone+"&email="+email;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onCheckOutListener.showResult();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void getOrderHistory(final IDataManager.onOrderHistoryListener onOrderHistoryListener, String api_key, String user_id, String mobile) {
        String URL = Constant.URL_ORDERHISTORY+"?api_key="+api_key+"&user_id="+user_id+"&mobile="+mobile;
        orderList = new ArrayList<OrderHistoryObject>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Order history");
                    for (int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject productlistobj = jsonArray.getJSONObject(i);
                        String orderid = productlistobj.getString("orderid");
                        String orderstatus = productlistobj.getString("orderstatus");
                        String name = productlistobj.getString("name");
                        String billingadd = productlistobj.getString("billingadd");
                        String deliveradd = productlistobj.getString("deliveryadd");
                        String mobile = productlistobj.getString("mobile");
                        String email = productlistobj.getString("email");
                        String itemid = productlistobj.getString("itemid");
                        String itemname = productlistobj.getString("itemname");
                        String itemquantity = productlistobj.getString("itemquantity");
                        String totalprice = productlistobj.getString("totalprice");
                        String paidprice = productlistobj.getString("paidprice");
                        String placedon = productlistobj.getString("placedon");

                        OrderHistoryObject orderHistoryObject = new OrderHistoryObject( orderid,  orderstatus,  name,  billingadd,  deliveradd,  mobile,  email,  itemid,  itemname,  itemquantity,  totalprice,  paidprice,  placedon);
                        orderList.add(orderHistoryObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onOrderHistoryListener.passOrderHistory(orderList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }


}
