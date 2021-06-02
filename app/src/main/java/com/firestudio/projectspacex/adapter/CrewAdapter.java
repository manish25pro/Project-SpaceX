package com.firestudio.projectspacex.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firestudio.projectspacex.Modal.CrewMember;
import com.firestudio.projectspacex.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewHolder> {
    private Context context;
    private List<CrewMember> crewMembers;

    public CrewAdapter(Context context, List<CrewMember> crewMembers) {
        this.context = context;
        this.crewMembers = crewMembers;
    }

    @NonNull
    @Override
    public CrewAdapter.CrewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crew_member_item, parent, false);
        return new CrewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapter.CrewHolder holder, int position) {
        CrewMember crewMember = crewMembers.get(position);
        holder.crewName.setText("Name: " + crewMember.getName());
        holder.crewAgency.setText("Agency: " + crewMember.getAgency());
        holder.crewId.setText("Id: " + crewMember.getId());
        holder.crewStatus.setText("Status: " + crewMember.getStatus());
        holder.crewHyperLink.setText("Link:" + crewMember.getWikipedia());
        Glide.with(context).load(crewMember.getImage()).into(holder.imageViewCrew);
        holder.crewHyperLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = crewMember.getWikipedia();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });

    }

    public void getAllCrew(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    @Override
    public int getItemCount() {
        return crewMembers.size();
    }

    public class CrewHolder extends RecyclerView.ViewHolder {
        TextView crewName, crewAgency, crewId, crewStatus, crewHyperLink;
        CircleImageView imageViewCrew;

        public CrewHolder(@NonNull View itemView) {
            super(itemView);
            crewName = itemView.findViewById(R.id.Crew_Member_Name);
            crewAgency = itemView.findViewById(R.id.Crew_Member_Agency);
            crewId = itemView.findViewById(R.id.Crew_Member_Id);
            crewStatus = itemView.findViewById(R.id.Crew_Member_status);
            crewHyperLink = itemView.findViewById(R.id.Crew_Member_hyperlink);
            imageViewCrew = itemView.findViewById(R.id.image_view_spaceX);


        }

    }
}
