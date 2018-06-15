package com.vaikunj.myinerview_app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaikunj.myinerview_app.Pojo.DsModel;
import com.vaikunj.myinerview_app.adapter.QuestionAnswerAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    ArrayList<DsModel> dsModels;
    String data;
    QuestionAnswerAdapter questionAnswerAdapter;
    LinearLayoutManager linearLayoutManager;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBar;
    RecyclerView recyclerView;
    CoordinatorLayout coordinatorLayout;
    TextView nodatafound;
     LinearLayout addbanner;
     String url="https://courses.learncodeonline.in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        addbanner=(LinearLayout)findViewById(R.id.addbanner);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinator_layout);
        appBar=(AppBarLayout)findViewById(R.id.app_bar);
        collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        nodatafound=(TextView) findViewById(R.id.nodatafound);
        toolbar.setTitle("LCO");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        dsModels=new ArrayList<>();


        if (getIntent().hasExtra("data")) {

            data = getIntent().getStringExtra("data");
            if (data != null) {
                Type type = new TypeToken<ArrayList<DsModel>>() {
                }.getType();
                Gson gson = new Gson();
                dsModels = gson.fromJson(data, type);
                Log.e("userdetails", "" + dsModels);
            }else{
                nodatafound.setVisibility(View.GONE);
            }

            Log.e("data", dsModels.toArray().toString());


        }


            questionAnswerAdapter = new QuestionAnswerAdapter(MainActivity.this, dsModels);
            linearLayoutManager = new LinearLayoutManager(MainActivity.this);

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(questionAnswerAdapter);

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);

            addbanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      openLCO();
                }


            });



    }

    private void openLCO() {
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Lco");
                String sAux = "\nThis application is for my spacial person\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }

            return true;
        }else if (id == R.id.feedback){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Review Your App");
            intent.setData(Uri.parse("mailto:vaikunjgandhi75@gmail.com.com"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }else if (id == R.id.rateus){
            Uri uri = Uri.parse("market://details?id=" + MainActivity.this.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
             intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
            }
            return true;
        }else if (id == R.id.about){
            openLCO();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
