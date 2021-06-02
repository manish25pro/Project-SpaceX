package com.firestudio.projectspacex.database;

import android.content.Context;
import android.media.AsyncPlayer;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.firestudio.projectspacex.Modal.CrewMember;
import com.firestudio.projectspacex.dao.SpaceXDao;

@Database(entities = {CrewMember.class}, version = 2)
public abstract class CrewMemberDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "CrewMemberDataBase";

    public abstract SpaceXDao spaceXDao();

    private static volatile CrewMemberDataBase INSTANCE;

    public static CrewMemberDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CrewMemberDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, CrewMemberDataBase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(callback).build();

                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new SpaceXAsyncTask(INSTANCE);

        }
    };

    static class SpaceXAsyncTask extends AsyncTask<Void, Void, Void> {
        private SpaceXDao spaceXDao;

        SpaceXAsyncTask(CrewMemberDataBase crewMemberDataBase) {
            spaceXDao = crewMemberDataBase.spaceXDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            spaceXDao.deleteAllCrewMember();
            return null;
        }
    }
}


