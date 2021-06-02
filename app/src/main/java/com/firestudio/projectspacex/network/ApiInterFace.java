package com.firestudio.projectspacex.network;

import com.firestudio.projectspacex.Modal.CrewMember;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterFace {

    @GET("v4/crew")
    Call<List<CrewMember>> crewMemberDataFetch();

}
