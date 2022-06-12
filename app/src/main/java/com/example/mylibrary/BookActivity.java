package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
//        String longDescription = "A young woman named Aomame follows a taxi driver's enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 “Q is for 'question mark.";
//
//        //TODO: Get the data from recycler view in here
//        Book book = new Book(1,"1Q84","Haruki Murakami",1350,"https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1519308101l/15772884._SY475_.jpg",
//                "A work of maddening brilliance",longDescription);
        Intent  intent = getIntent();

        if(intent != null){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(incomingBook != null){
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavoriteBooks = false;

        for(Book b:favoriteBooks){
            if(b.getId() == book.getId()){
                existInFavoriteBooks = true;
                break;
            }
        }
        if(existInFavoriteBooks){
            btnAddToFavorite.setEnabled(false);
        }else{
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavorite(book)){
                        Toast.makeText(BookActivity.this,"Book Added",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,FavoriteActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this,"Error happened, please try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;

        for(Book b:currentlyReadingBooks){
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
                break;
            }
        }
        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this,"Book Added",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this,"Error happened, please try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;

        for(Book b:wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
                break;
            }
        }
        if(existInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this,"Book Added",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this,"Error happened, please try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*
* Enable and Disable button
* Add the book to Already Read Book Lists
* */
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(BookActivity.this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;

        for(Book b:alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
                break;
            }
        }
        if(existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this,"Book Added",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this,"Error happened, please try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }

    private void initViews() {

        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);

        bookImage = findViewById(R.id.imgBook);
    }
}