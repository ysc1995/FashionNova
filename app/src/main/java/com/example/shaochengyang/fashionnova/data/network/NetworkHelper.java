package com.example.shaochengyang.fashionnova.data.network;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shaochengyang.fashionnova.AppController;
import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.data.network.model.SubCategory;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NetworkHelper implements INetworkHelper {
    List<Collection> collectionList ;
    List<SubCategory> subCategoryList;
    private static final String TAG = "NetworkHelper";
    private String urlCollection = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=5ec23161979dd69909960de49e6db800&user_id=1384";
    private String urlSubCategory = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=107&api_key=5ec23161979dd69909960de49e6db800&user_id=1384";


    @Override
    public void getCollection(final IDataManager.OnCollectionListener onCollectionListener) {
        // perform the volley n/w call
        collectionList = new ArrayList<Collection>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                urlCollection, null, new Response.Listener<JSONObject>() {
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
    public void getSubCategory(final IDataManager.onSubCategoryListener onSubCategoryListener) {

        subCategoryList = new ArrayList<SubCategory>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                urlSubCategory, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("Id","107");
                params.put("api_key","5ec23161979dd69909960de49e6db800");
                params.put("user_id","1384");
                return super.getParams();
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }



}
