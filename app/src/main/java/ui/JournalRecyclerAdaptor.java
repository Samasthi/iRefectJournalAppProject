package ui;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.srdd.ireflect.R;

import java.util.List;

import model.Journal;

import static android.content.Intent.ACTION_SEND;

public class JournalRecyclerAdaptor extends RecyclerView.Adapter<JournalRecyclerAdaptor.ViewHolder> {
    private Context context;
    private List<Journal> journalList;

    public JournalRecyclerAdaptor(Context context, List<Journal> journalList) {
        this.context = context;
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public JournalRecyclerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.journal_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalRecyclerAdaptor.ViewHolder holder, int position) {
        Journal journal = journalList.get(position);
        String imageUrl;

        holder.title.setText(journal.getTitle());
        holder.thoughts.setText(journal.getThought());
        holder.name.setText(journal.getUserName());
        imageUrl = journal.getImageUrl();
        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(journal.getTimeAdded().getSeconds() * 1000);
        holder.dateAdded.setText(timeAgo);


        //use picasso library to download and show image.

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.scape)
                .fit()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title, thoughts, dateAdded, name;
        public ImageView image;
        public ImageButton shareButton;
        String userId;
        String username;


        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            title = itemView.findViewById(R.id.journal_title_list);
            thoughts = itemView.findViewById(R.id.journal_thought_list);
            dateAdded = itemView.findViewById(R.id.journal_timestamp_list);
            image = itemView.findViewById(R.id.journal_image_list);
            name = itemView.findViewById(R.id.journal_row_username);
            shareButton = itemView.findViewById(R.id.journal_row_share_button);

            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(ACTION_SEND).setType("image/*"));
                }
            });

        }
    }
}
