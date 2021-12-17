package com.example.librarybooksnotes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.librarybooksnotes.pojo.BooksData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBook extends AppCompatActivity {
  EditText et_add_title , et_add_author , et_add_pages_numbers;
  Button btn_add_to_database;
  DatabaseAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        et_add_title = findViewById(R.id.add_title);
        et_add_author = findViewById(R.id.add_author);
        et_add_pages_numbers = findViewById(R.id.add_author);


        btn_add_to_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booksAdapter = new DatabaseAdapter(AddBook.this);
                BooksData booksData = new BooksData(et_add_title.getText().toString().trim(),
                        et_add_author.getText().toString().trim(),
                        Integer.valueOf(et_add_pages_numbers.getText().toString().trim()));

                booksAdapter.addBook(booksData);


            }
        });

    }
}