package com.example.callforcode.Services;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.callforcode.Fragments.HomeFragment;
import com.example.callforcode.Model.Bussines;
import com.example.callforcode.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BussinesService {
    private static BussinesService instance = new BussinesService();

    public static BussinesService getInstance() {
        return instance;
    }

    private BussinesService(){

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////Request all Bussines//////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Bussines> getAllBussies(Context context, final HomeFragment.placesDL listener ){
        String url = "https://app2450323.us-south.cf.appdomain.cloud";
        final ArrayList<Bussines> bussinesList = new ArrayList<>();
        final JsonArrayRequest getBussines = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONArray bussines = response;

                    for (int i = 0; i < bussines.length(); i++) {
                        JSONObject bus = bussines.getJSONObject(i);
                        Log.i("RESPONSE", bussines.toString());
                        String name = bus.getString("name");
                        String  category = bus.getString("category");
                        //String id = bus.getString("_id");
                        String description = bus.getString("description");

                        JSONObject location = bus.getJSONObject("location");
                        Double lat = location.getDouble("lat");
                        Double lon = location.getDouble("long");
                        String status = bus.getString("status");


                        Bussines newBussines = new Bussines(name,description,lat,lon,status,category);

                        Log.i("NEW BUSSINES", newBussines.toString());
                        bussinesList.add(newBussines);

                    }
                } catch (JSONException e) {
                    Log.v("JSON", "EXC" + e.getLocalizedMessage());
                }
                listener.success(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("api", "Error-------------------------------------------------------------------------------" + error.getLocalizedMessage());
            }
        });
        Volley.newRequestQueue(context).add(getBussines);
        Log.i("RETURN", bussinesList.toString());
        return bussinesList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////Register  Bussines//////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void registerBussines(Context context, final String name, final String category, final String description, final String lon, final String lat) throws AuthFailureError, JSONException {
        String url = "https://app2450323.us-south.cf.appdomain.cloud/api/shop";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject params = new JSONObject();

        params.put("shop_name", name);
        params.put("shop_cat",category);
        params.put("shop_desc",description);
        JSONObject location = new JSONObject();
        location.put("lat",lat);
        location.put("long",lon);
        params.put("shop_location", location);
        params.put("shop_status","close");
        Log.i("PARAMS",params.toString());
        final String mRequestBody = params.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_RESPONSE", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }


}
