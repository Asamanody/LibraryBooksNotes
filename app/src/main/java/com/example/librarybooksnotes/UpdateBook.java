package com.example.librarybooksnotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.librarybooksnotes.pojo.BooksData;

public class UpdateBook extends AppCompatActivity {
    EditText et_update_title , et_update_author , et_update_pages_numbers;
    Button btn_update_in_database;
    DatabaseAdapter booksAdapter;


    String title , author;
    int numberOfPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        et_update_title = findViewById(R.id.update_title);
        et_update_author= findViewById(R.id.update_author);
        et_update_pages_numbers = findViewById(R.id.update_pages_numbers);
        btn_update_in_database = findViewById(R.id.update_in_database);

        getAndSetData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        btn_update_in_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booksAdapter = new DatabaseAdapter(UpdateBook.this);
                BooksData booksData = new BooksData(et_update_title.getText().toString().trim(),
                        et_update_author.getText().toString().trim(),
                        Integer.valueOf(et_update_pages_numbers.getText().toString().trim()));
                booksAdapter.updateBook(booksData);


            }
        });

    }

    // get data from main activity and set it to the edit texts

    public void getAndSetData()
    {
        if (getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("numberOfPages"))
        {
            title = getIntent().getStringExtra("title");
            author= getIntent().getStringExtra("author");
            numberOfPages = getIntent().getIntExtra("numberOfPages",1);

            et_update_title.setText(title);
            et_update_author.setText(author);
            et_update_pages_numbers.setText(numberOfPages);
        }else
            Toast.makeText(UpdateBook.this,"no data ",Toast.LENGTH_LONG).show();


    }
}