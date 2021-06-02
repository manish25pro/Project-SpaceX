package com.firestudio.projectspacex.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.firestudio.projectspacex.Modal.CrewMember;

import java.util.List;

@Dao
public interface SpaceXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCrewMember(List<CrewMember> crewMemberList);

    @Query("DELETE FROM crewMemberTable")
    void deleteAllCrewMember();

    @Query("SELECT * FROM crewMemberTable")
    LiveData<List<CrewMember>> getAllCrewMembers();
}
