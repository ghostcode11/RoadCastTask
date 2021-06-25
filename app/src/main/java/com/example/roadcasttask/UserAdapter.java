package com.example.roadcasttask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadcasttask.roomr.UserEn;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<UserEn> userEnList;
    Context context;

    public UserAdapter(Context context, List<UserEn> userEnList){
        this.context = context;
        this.userEnList = userEnList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userl_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        holder.tvid.setText("User-ID: "+String.valueOf(userEnList.get(position).getId()));
        holder.tvname.setText("Name: "+userEnList.get(position).getName());
        holder.tvusername.setText("UserName: "+userEnList.get(position).getUsername());
        holder.tvemail.setText("Email: "+userEnList.get(position).getEmail());
        holder.tvphone.setText("Phone: "+userEnList.get(position).getPhone());
        holder.tvwebsite.setText("Website: "+userEnList.get(position).getWebsite());

        holder.tvstreet.setText("Street: "+userEnList.get(position).getAddressModel().getStreet());
        holder.tvsuite.setText("Suite: "+userEnList.get(position).getAddressModel().getSuite());
        holder.tvcity.setText("City: "+userEnList.get(position).getAddressModel().getCity());
        holder.tvzipcode.setText("zipcode: "+userEnList.get(position).getAddressModel().getZipcode());
        holder.tvgeo.setText("Latitude: "+userEnList.get(position).getAddressModel().getGeoModel().getLat()+"    "
                +"Longitude: "+userEnList.get(position).getAddressModel().getGeoModel().getLng());

        holder.tvcname.setText("C. Name: "+userEnList.get(position).getCompanyModel().getName());
        holder.tvcatchphrase.setText("Catch Phrase: "+userEnList.get(position).getCompanyModel().getCatchPhrase());
        holder.tvbs.setText("Bs: "+userEnList.get(position).getCompanyModel().getBs());

    }

    @Override
    public int getItemCount() {
        return userEnList.size();
    }

    public void updateList(List<UserEn> list){
        userEnList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvid, tvname, tvusername, tvemail,tvwebsite,tvphone,
        tvstreet, tvsuite,tvcity,tvzipcode,tvgeo,
        tvcname,tvcatchphrase,tvbs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id);
            tvname = itemView.findViewById(R.id.tv_name);
            tvusername = itemView.findViewById(R.id.tv_username);
            tvemail = itemView.findViewById(R.id.tv_email);
            tvwebsite = itemView.findViewById(R.id.tv_website);
            tvphone = itemView.findViewById(R.id.tv_phone);

            tvstreet = itemView.findViewById(R.id.tv_street);
            tvsuite = itemView.findViewById(R.id.tv_suite);
            tvcity = itemView.findViewById(R.id.tv_city);
            tvzipcode = itemView.findViewById(R.id.tv_zipcode);
            tvgeo = itemView.findViewById(R.id.tv_geo);

            tvcname = itemView.findViewById(R.id.tv_cname);
            tvcatchphrase = itemView.findViewById(R.id.tv_catch_phrase);
            tvbs = itemView.findViewById(R.id.tv_bs);

        }
    }
}
