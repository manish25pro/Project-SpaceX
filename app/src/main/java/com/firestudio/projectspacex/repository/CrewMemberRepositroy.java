package com.firestudio.projectspacex.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.firestudio.projectspacex.Modal.CrewMember;
import com.firestudio.projectspacex.dao.SpaceXDao;
import com.firestudio.projectspacex.database.CrewMemberDataBase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CrewMemberRepositroy {
    private CrewMemberDataBase dataBase;
    private LiveData<List<CrewMember>> crewMemberList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public CrewMemberRepositroy(Application application) {
        dataBase = CrewMemberDataBase.getInstance(application);
        crewMemberList = dataBase.spaceXDao().getAllCrewMembers();
    }

    public void insert(List<CrewMember> crewList) {
        new InsertAsyncTask(dataBase).execute(crewList);

    }

    public void deleteCrewMemberAll(){
mExecutor.execute(new Runnable() {
    @Override
    public void run() {
        dataBase.spaceXDao().deleteAllCrewMember();
    }
});
    }

    public LiveData<List<CrewMember>> getCrewMemberList() {
        return crewMemberList;
    }

    static class InsertAsyncTask extends AsyncTask<List<CrewMember>, Void, Void> {
        private SpaceXDao spaceXDao;

        public InsertAsyncTask(CrewMemberDataBase crewMemberDataBase) {
            spaceXDao = crewMemberDataBase.spaceXDao();
        }

        @Override

        protected Void doInBackground(List<CrewMember>... lists) {
            spaceXDao.insertCrewMember(lists[0]);
            return null;
        }
    }
}
