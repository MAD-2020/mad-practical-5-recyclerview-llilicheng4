package sg.edu.np.mad.mad_recyclerview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    final String TAG = "button activity";
    ArrayList<Task> data;

    public Adapter(ArrayList<Task> input){
        //be careful with here because sometimes to get the data you need to copy instead of "="
        data = input;

    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(item);

    }
    public void onBindViewHolder(ViewHolder holder, final int position){
        //Using the row id to retrieve data from list
        String s = data.get(position).getName();
        //Display the information on to the UI
        holder.txt.setText(s);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked");
                String positionName = data.get(position).getName();
                Log.d(TAG, String.valueOf(positionName));
                //add dialogue page and delete func
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete");
                builder.setTitle("Are you sure you want to delete\n"+positionName+"?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        notifyDataSetChanged();
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.setCancelable(false);
                alert.show();
            }
        });
    }
    public int getItemCount() {
        return data.size();
    }
}
