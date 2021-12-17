package com.example.librarybooksnotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.librarybooksnotes.pojo.BooksData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnItemClick{
    RecyclerView recyclerView ;
    FloatingActionButton btn_add;
    DatabaseAdapter booksAdapter;
    ArrayList<BooksData> data;
    MyReAdapter myReAdapter ;
    RecyclerView.LayoutManager layoutManager ;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.book_recyclerView);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddBook.class);
                startActivity(intent);

            }
        });
        booksAdapter = new DatabaseAdapter(MainActivity.this); // sql

        storeData();


        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        myReAdapter = new MyReAdapter(MainActivity.this,data,this);
        recyclerView.setAdapter(myReAdapter);
        // like getActivity result in start activity for result
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== RESULT_OK && result.getData() !=null ){
                    recreate();

                }

            }
        });


    }
    void storeData()
    {
        data.add(booksAdapter.readFromDataBase());

    }

    // recyclerView item click to navigate to the update acticvity

    @Override
    public void onItemCLick(int position) {
        Intent intent = new Intent(MainActivity.this,UpdateBook.class);
        intent.putExtra("title",String.valueOf(data.get(position).getBook_title()));
        intent.putExtra("author",String.valueOf(data.get(position).getBook_author()));
        intent.putExtra("numberOfPages",Integer.valueOf(data.get(position).getBook_pages_numbers()));
        activityResultLauncher.launch(intent);

    }


}