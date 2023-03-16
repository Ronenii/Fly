package com.GroupC.fly.data.Objects;
import java.time.LocalDateTime;

/**
 * This class handles the abstract post class that is the "father" of the different types of posts we will have.
 *
 * **/

abstract class Post {

    /** DATA MEMBERS **/
    private int m_id, m_likes = 0;
    private String m_owner;

    private LocalDateTime m_dateTime = null;
    private Reply[] replies;

    // TODO: Content type;
    //TODO: Content;


    /** ABSTRACT METHODS **/

    //This method will update the post in the post DB on firebase.
    //A virtual method that differs between MasterPost and Reply.
    abstract public void updatePostInDB();


    //This method will add the post into the post DB on firebase.
    //A virtual method that differs between MasterPost and Reply.
    abstract public void createPostInDB();


    //This method will delete the post from the post DB on firebase.
    //A virtual method that differs between MasterPost and Reply.
    abstract public void deletePostInDB();

    /** METHODS **/
    public Post(){}
    public Post(String i_owner, int i_likes, LocalDateTime i_dateTime)
    {
        this.m_dateTime = i_dateTime;
        this.m_likes = i_likes;
        this.m_owner = i_owner;
    }

    public int getM_id() {
        return m_id;
    }

    public void set_id(int m_id) {
        this.m_id = m_id;
    }

    public int get_likes() {
        return m_likes;
    }

    public void set_likes(int m_likes) {
        this.m_likes = m_likes;
    }

    public String get_owner() {
        return m_owner;
    }

    public void set_owner(String m_owner) {
        this.m_owner = m_owner;
    }

    public LocalDateTime get_dateTime() {
        return m_dateTime;
    }

    public void set_dateTime(LocalDateTime m_dateTime) {
        this.m_dateTime = m_dateTime;
    }

    public Reply[] getReplies() {
        return replies;
    }

    public void setReplies(Reply[] replies) {
        this.replies = replies;
    }

    //This method is activated once the post gets a like
    public void recieveLike()
    {
        m_likes++;
    }

    //This method is activated once the post is unlike
    public void recieveUnlike() {
        m_likes--;
    }




}
