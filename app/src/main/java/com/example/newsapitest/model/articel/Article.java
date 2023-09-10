package com.example.newsapitest.model.articel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "articles")
public class Article implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String author;
    private String urlToImage;
    private String publishedAt;
    private String description;
    private String url;

    public Article() {
    }

    protected Article(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        description = in.readString();
        url = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(urlToImage);
        parcel.writeString(publishedAt);
        parcel.writeString(description);
        parcel.writeString(url);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
