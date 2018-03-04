package com.corneloaie.android.top10devs.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.corneloaie.android.top10devs.volley.request.RequestJSONObject;

import org.json.JSONObject;

/**
 * Created by Cornel-PC on 04/03/2018
 */

public class VolleyHelper {

    private static final String apiBaseUrl = "https://api.stackexchange.com";
    private static String TAG = "VolleyHelper";
    private static VolleyHelper volleyHelper;
    private static Context context;
    private RequestQueue requestQueue;

    private VolleyHelper(Context context) {
        VolleyHelper.context = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized void initInstance(Context context) {
        if (volleyHelper == null) {
            volleyHelper = new VolleyHelper(context);
        }
    }

    public static synchronized VolleyHelper getInstance() {
        if (volleyHelper == null) {
            throw new RuntimeException("Not initialized");
        }
        return volleyHelper;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void get(String resourceName, final VolleyCallback callback, final Context context) {
        RequestQueue localRequestQueue = getRequestQueue();
        String url = apiBaseUrl + "/" + resourceName;

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        };

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, "onResponse = \n " + response.toString());
                callback.onSuccess(response);
            }
        };
        localRequestQueue.add(new RequestJSONObject(Request.Method.GET, url, null /* JSON object*/, responseListener,
                errorListener));
    }

}
