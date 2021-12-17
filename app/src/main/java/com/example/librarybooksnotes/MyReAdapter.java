package com.example.librarybooksnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.librarybooksnotes.pojo.BooksData;


public class MyReAdapter extends RecyclerView.Adapter<MyReAdapter.MyViewHolder> {
  private Context context ;
  private ArrayList<BooksData> booksData ;
  private RecyclerViewOnItemClick recyclerViewOnItemClick;

    public MyReAdapter(Context context, ArrayList<BooksData> booksData, RecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.context = context;
        this.booksData = booksData;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
         View v= inflater.inflate(R.layout.book_row,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.itemView.setTag(booksData.get(position));
      holder.book_id.setText(String.valueOf(booksData.get(position)));
      holder.book_title.setText(booksData.get(position).getBook_title());
      holder.book_author.setText(booksData.get(position).getBook_author());
      holder.book_author.setText(String.valueOf(booksData.get(position).getBook_pages_numbers()));


    }

    @Override
    public int getItemCount() {
        return booksData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      TextView book_id ,book_title ,book_author ,book_pages ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id = itemView.findViewById(R.id.book_id);
            book_title = itemView.findViewById(R.id.book_title);
            book_author = itemView.findViewById(R.id.book_author);
            book_pages = itemView.findViewById(R.id.book_pages);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAbsoluteAdapterPosition();
            recyclerViewOnItemClick.onItemCLick(clickedPosition);
        }
    }
}
