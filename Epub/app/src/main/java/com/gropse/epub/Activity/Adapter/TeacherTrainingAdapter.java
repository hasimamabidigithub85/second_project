package com.gropse.epub.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gropse.epub.Activity.Model.BookModel;
import com.gropse.epub.Activity.Model.GetTitleModel;
import com.macreel.epauthi.R;

import java.util.ArrayList;

public class TeacherTrainingAdapter extends RecyclerView.Adapter<TeacherTrainingAdapter.MyViewHolder> {
    ArrayList<GetTitleModel> list = new ArrayList<>();
    Context context;
    public View.OnClickListener clickListener;

    public TeacherTrainingAdapter(Context context, ArrayList<GetTitleModel> list) {
        this.context = context;
        this.list = list;
    }
    public void onClick(View.OnClickListener v) {
        clickListener = v;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_training_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(clickListener);

//        holder.download_img.setTag(holder.download_img.getId(),position);
//        holder.download_img.setOnClickListener(clickListener);

       /* holder.view_img.setTag(holder.view_img.getId(),position);
        holder.view_img.setOnClickListener(clickListener);*/

        holder.title_name.setText(list.get(position).tittle);
    }

    /*  public void addList(ArrayList newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
*/
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title_name, text1, description;
        ImageView view_img,download_img;
        Button btn_view,btn_download;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_img = itemView.findViewById(R.id.view_img);
        //    btn_view = itemView.findViewById(R.id.btn_view);
            download_img = itemView.findViewById(R.id.download_img_teacher);
         //   btn_download = itemView.findViewById(R.id.btn_download);
         //   name = itemView.findViewById(R.id.gearRequestname);

           // title_name = itemView.findViewById(R.id.txt_title);
            title_name = itemView.findViewById(R.id.title_name_teacher);
        }
    }


}
