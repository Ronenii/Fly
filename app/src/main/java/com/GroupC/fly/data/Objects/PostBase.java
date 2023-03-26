package com.GroupC.fly.data.Objects;
import android.net.Uri;
import android.widget.TextView;

import java.time.LocalDateTime;

/**
 * This class handles the abstract post class that is the "father" of the different types of posts we will have.
 * **/
abstract class PostBase {

    /** DATA MEMBERS **/
    private int id, likes = 0;
    private String owner;

    private LocalDateTime dateTime = null;
    private Reply[] replies;

    private TextView postTitle;
    private TextView postDesc;
    private Uri imageUri;

    // TODO: Content type;
    //TODO: Content;


    /** ABSTRACT METHODS **/

    /**This method will update the post in the post DB on firebase.
    A virtual method that differs between MasterPost and Reply.**/
    abstract public void updatePostInDB();


    /**This method will add the post into the post DB on firebase.
    A virtual method that differs between MasterPost and Reply.**/
    abstract public void createPostInDB();


    /**This method will delete the post from the post DB on firebase.
    A virtual method that differs between MasterPost and Reply.**/
    abstract public void deletePostInDB();

    /** METHODS **/
    public PostBase(){}
    public PostBase(String owner, int likes, LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
        this.likes = likes;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPostTitle(TextView postTitle) { this.postTitle = postTitle;}
    public void setPostDesc(TextView postDesc) { this.postDesc = postDesc;}
    public void setImgUri(Uri imgUri) { imageUri = imgUri;}

    public TextView getTitle() { return postTitle; }
    public TextView getDesc() { return postDesc; }
    public Uri getImageUri() { return imageUri; }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Reply[] getReplies() {
        return replies;
    }

    public void setReplies(Reply[] replies) {
        this.replies = replies;
    }

    //This method is activated once the post gets a like
    public void receiveLike()
    {
        likes++;
    }

    //This method is activated once the post is unlike
    public void receiveUnlike() {
        likes--;
    }
}
