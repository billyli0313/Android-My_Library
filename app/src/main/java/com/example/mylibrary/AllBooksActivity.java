package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
//Set animation
//        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
//Return back Home page on the top button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new BookRecViewAdapter(this,"allBooks");
        booksRecView = findViewById(R.id.bookRecView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));
//        ArrayList<Book> books = new ArrayList<>();
//        books.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1519308101l/15772884._SY475_.jpg",
//                "A work of maddening brilliance","Long Description"));
//        books.add(new Book(2,"The Myth of Sisyphus","Albert Camus",250,"https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1533718219l/11987.jpg",
//                "Camus introduces his philosophy of the absurd.","Long Description"));
        adapter.setBooks(Utils.getInstance(this).getAllBooks());
    }
//Set back animation
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}