package com.example.roadcasttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadcasttask.models.AddressModel;
import com.example.roadcasttask.models.CompanyModel;
import com.example.roadcasttask.models.GeoModel;
import com.example.roadcasttask.models.UserModel;
import com.example.roadcasttask.roomr.DatabaseClient;
import com.example.roadcasttask.roomr.UserEn;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<UserModel> userModelList;
    RecyclerView recyclerView;
    EditText ed_search;
    List<UserEn> userEnList;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getAllUsers();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.user_view);
        ed_search = findViewById(R.id.search_ed);

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }


        });
    }

    private void filter(String text){
        List<UserEn> temp = new ArrayList();
        for(UserEn d: userEnList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        userAdapter.updateList(temp);
    }


    private void getAllUsers(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
       Call<List<UserModel>> call = requestInterface.getUsers();
       call.enqueue(new Callback<List<UserModel>>() {
           @Override
           public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
               progressDialog.dismiss();
               userModelList = response.body();
               if (!userModelList.isEmpty()){
                   SaveUser saveUser = new SaveUser();
                   saveUser.execute();
               }else{
                   Toast.makeText(MainActivity.this, "empty list", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<UserModel>> call, Throwable t) {
               progressDialog.dismiss();
               Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void getUser() {

        class GetTasks extends AsyncTask<Void, Void, List<UserEn>> {

            @Override
            protected List<UserEn> doInBackground(Void... voids) {
                 userEnList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAll();
                return userEnList;
            }

            @Override
            protected void onPostExecute(List<UserEn> userEnList) {
                super.onPostExecute(userEnList);
                if (!userEnList.isEmpty()){
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    userAdapter = new UserAdapter(MainActivity.this, userEnList);
                    recyclerView.setAdapter(userAdapter);
                }else {
                    Toast.makeText(MainActivity.this, "nothing from room", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    class SaveUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            List<UserEn> userEn = new ArrayList<>();

            for (int i=0 ; i<userModelList.size();i++) {
                AddressModel addressModel = new AddressModel();
                addressModel.setCity(userModelList.get(i).getAddress().getCity());
                addressModel.setStreet(userModelList.get(i).getAddress().getStreet());
                addressModel.setSuite(userModelList.get(i).getAddress().getSuite());
                addressModel.setZipcode(userModelList.get(i).getAddress().getZipcode());

                GeoModel geoModel = new GeoModel();
                geoModel.setLat(userModelList.get(i).getAddress().getGeoModel().getLat());
                geoModel.setLng(userModelList.get(i).getAddress().getGeoModel().getLng());
                addressModel.setGeoModel(geoModel);

                CompanyModel companyModel = new CompanyModel();
                companyModel.setBs(userModelList.get(i).getCompanyModel().getBs());
                companyModel.setCatchPhrase(userModelList.get(i).getCompanyModel().getCatchPhrase());
                companyModel.setName(userModelList.get(i).getCompanyModel().getName());

                UserEn userEn1 = new UserEn();
                userEn1.setId(Integer.parseInt(userModelList.get(i).getId()));
                userEn1.setName(userModelList.get(i).getName());
                userEn1.setUsername(userModelList.get(i).getUsername());
                userEn1.setEmail(userModelList.get(i).getEmail());
                userEn1.setPhone(userModelList.get(i).getPhone());
                userEn1.setWebsite(userModelList.get(i).getWebsite());

                userEn1.setCompanyModel(companyModel);
                userEn1.setAddressModel(addressModel);
                userEn.add(userEn1);
            }
            //adding to database
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .userDao()
                    .insert(userEn);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getUser();
        }
    }


}
