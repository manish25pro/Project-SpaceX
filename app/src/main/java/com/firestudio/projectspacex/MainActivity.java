package com.firestudio.projectspacex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firestudio.projectspacex.Modal.CrewMember;
import com.firestudio.projectspacex.adapter.CrewAdapter;
import com.firestudio.projectspacex.network.ApiClient;
import com.firestudio.projectspacex.network.ApiInterFace;
import com.firestudio.projectspacex.repository.CrewMemberRepositroy;
import com.firestudio.projectspacex.viewmodel.CrewViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CrewViewModel crewViewModel;
    RecyclerView recyclerView;
    private CrewAdapter crewAdapter;
    private List<CrewMember> crewMembers;
    private CrewMemberRepositroy crewMemberRepositroy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        crewMemberRepositroy = new CrewMemberRepositroy(getApplication());
        crewMembers = new ArrayList<>();
        crewAdapter = new CrewAdapter(this, crewMembers);
        crewViewModel = new ViewModelProvider(this).get(CrewViewModel.class);
        crewViewModel.getAllCrew().observe(this, new Observer<List<CrewMember>>() {
            @Override
            public void onChanged(List<CrewMember> crewMembers) {
                crewAdapter.getAllCrew(crewMembers);
                recyclerView.setAdapter(crewAdapter);
            }
        });


        loadData();


    }

    private void loadData() {
        ApiInterFace apiInterFace = ApiClient.getRetrofitObject().create(ApiInterFace.class);
        Call<List<CrewMember>> CrewMemberList = apiInterFace.crewMemberDataFetch();
        CrewMemberList.enqueue(new Callback<List<CrewMember>>() {
            @Override
            public void onResponse(Call<List<CrewMember>> call, Response<List<CrewMember>> response) {
                if (response.isSuccessful()) {
crewMembers = response.body();
                    crewViewModel.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CrewMember>> call, Throwable t) {
                Log.d("data", "" + t.getMessage());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete:
                crewViewModel.deleteCrewMember();
                return true;
            case R.id.refersh:
                crewViewModel.insert(crewMembers);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
