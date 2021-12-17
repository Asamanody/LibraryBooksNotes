package com.example.librarybooksnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.librarybooksnotes.pojo.BooksData;

class DatabaseAdapter {
    private  Context context;

   BooksDatabase dbHelper ;


    public DatabaseAdapter(Context context)
    {
        dbHelper = new BooksDatabase(context);

    }

    public void addBook (BooksData booksData)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BooksDatabase.COULOMNS_TITLE,booksData.getBook_title());
        contentValues.put(BooksDatabase.COULOMNS_AUTHOR,booksData.getBook_author());
        contentValues.put(BooksDatabase.COULOMNS_PAGES,booksData.getBook_pages_numbers());

       long id = db.insert(BooksDatabase.TABLE_NAME,null,contentValues);
       if(id > 0) {
           Toast.makeText(context,"added succes",Toast.LENGTH_LONG).show();
       }else {
           Toast.makeText(context,"added faild",Toast.LENGTH_LONG).show();
       }


    }
    public void updateBook (BooksData booksData)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BooksDatabase.COULOMNS_TITLE,booksData.getBook_title());
        contentValues.put(BooksDatabase.COULOMNS_AUTHOR,booksData.getBook_author());
        contentValues.put(BooksDatabase.COULOMNS_PAGES,booksData.getBook_pages_numbers());

        long id = db.update(BooksDatabase.TABLE_NAME,contentValues,"id=?",new String[]{booksData.getBook_title()});
        if(id > 0) {
            Toast.makeText(context,"added succes",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"added faild",Toast.LENGTH_LONG).show();
        }


    }
    public BooksData readFromDataBase()
    {
        BooksData data = null ;
        Cursor c ;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
         String [] coulomns ={BooksDatabase.COULOMNS_TITLE,BooksDatabase.COULOMNS_AUTHOR,BooksDatabase.COULOMNS_PAGES};
         c= db.query(BooksDatabase.TABLE_NAME,coulomns,null,null,null,null,null); // from table , coulomns names , conditions (where )
        if(c.getCount()== 0){
            Toast.makeText(context,"added succes",Toast.LENGTH_LONG).show();

        }
        while (c.moveToNext())
        {
            data = new BooksData(c.getString(0),c.getString(1),c.getInt(2));

        }
        return data;

    }


    public class BooksDatabase extends SQLiteOpenHelper {

        private static final String DATABASE_NAME="library.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "books";
        private static final String COULOMNS_ID = "id";
        private static final String COULOMNS_TITLE = "title";
        private static final String COULOMNS_AUTHOR = "author";
        private static final String COULOMNS_PAGES = "pages";
        private static final String QUARY_STATMENT = "CREATE TABLE " + TABLE_NAME +
                " (" + COULOMNS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COULOMNS_TITLE +" TEXT," +
                COULOMNS_AUTHOR+ " TEXT," +
                COULOMNS_PAGES +" TEXT );";


        public BooksDatabase(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(QUARY_STATMENT);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);

        }
    }



}

