package com.example.quiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://jsonplaceholder.typicode.com/todos";

    ListView listView;
    List<Todo> todoList;

    ContentValues contentValues = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing listview and hero list
        listView = findViewById(R.id.listView);
        todoList = new ArrayList<>();

        DbHelper sqlHelper = new DbHelper(this);
        SQLiteDatabase sql = sqlHelper.getWritableDatabase();


        loadHeroList();
        sql.insert("users", null, contentValues);

        StringBuilder result = new StringBuilder();
        Cursor selectData = sql.query("users", null, null, null, null, null, null);
        if (selectData.moveToFirst()) {
            do {
                int getId = selectData.getInt(selectData.getColumnIndex("customId"));
                String getTitle = selectData.getString(selectData.getColumnIndex("title"));
                String getUserId = selectData.getString(selectData.getColumnIndex("userId"));
                String getCompleted = selectData.getString(selectData.getColumnIndex("completed"));
                result.append(getId).append(", ").append(getTitle).append(", ").append(getUserId).append(", ").append(getCompleted);
            } while (selectData.moveToNext());
        }

        String a = "a";
    }

    private void loadHeroList() {

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < 50; i++) {
                                JSONObject object = array.getJSONObject(i);
                                contentValues.put("customId", object.getString("id"));
                                contentValues.put("title", object.getString("title"));
                                contentValues.put("userId", object.getString("userId"));
                                contentValues.put("completed", object.getString("completed"));
                                Todo todo = new Todo(
                                        object.getString("id"),
                                        object.getString("title"),
                                        object.getString("userId"),
                                        object.getString("completed"));
                                todoList.add(todo);
                            }
                            ListViewAdapter adapter = new ListViewAdapter(todoList, getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
