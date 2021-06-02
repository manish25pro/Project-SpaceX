package com.firestudio.projectspacex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.firestudio.projectspacex.Modal.CrewMember;
import com.firestudio.projectspacex.repository.CrewMemberRepositroy;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {
    private CrewMemberRepositroy crewMemberRepositroy;
    private LiveData<List<CrewMember>>getAllCrewMembers;

    public CrewViewModel(@NonNull Application application) {
        super(application);
    crewMemberRepositroy = new CrewMemberRepositroy(application);
    getAllCrewMembers =crewMemberRepositroy.getCrewMemberList();

    }

    public  void insert(List<CrewMember>list){
        crewMemberRepositroy.insert(list);
    }

    public LiveData<List<CrewMember>>getAllCrew(){
        return  getAllCrewMembers;
    }

    public void deleteCrewMember(){
        crewMemberRepositroy.deleteCrewMemberAll();
    }
}
