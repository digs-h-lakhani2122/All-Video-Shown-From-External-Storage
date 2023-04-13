package coatocl.exaatocl.videoshowfromgallery;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{

    private  final RecyclerViewInterface recyclerViewInterface;
    Context mcontext;

    Adapter (Context mcontext,RecyclerViewInterface recyclerViewInterface)

    {
       this.mcontext =mcontext;
       this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.file_list,parent,false);

        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.path.setText(Constant.allMediaList.get(position).getName());

//        using glide
        Uri uri=Uri.fromFile(Constant.allMediaList.get(position));
        Glide.with(mcontext).load(uri).placeholder(R.drawable.ic_launcher_background).into(holder.picture);
    }

    @Override
    public int getItemCount()
    {
        return Constant.allMediaList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView picture;
        TextView path;
        ImageButton viewButton;

        public ViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            picture = itemView.findViewById(R.id.picture);
            path = itemView.findViewById(R.id.path);
            viewButton = itemView.findViewById(R.id.viewButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(recyclerViewInterface!=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
