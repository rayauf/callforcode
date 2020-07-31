package com.example.callforcode.Services;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.callforcode.Fragments.HomeFragment;
import com.example.callforcode.Model.Bussines;
import com.google.android.gms.common.internal.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

}
