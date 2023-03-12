package com.GroupC.fly.data.Objects;
import java.time.LocalDateTime;

/**
 * This class handles the abstract post class that is the "father" of the different types of posts we will have.
 *
 * **/

abstract class Post {

    /* Data Members */
    private int id, likes;
    String owner;
    //Content type;
    //Content;
    private LocalDateTime date_time = null;


    /* Functions */

    public Post(){}
    public Post(String owner, int likes, LocalDateTime date_time)
    {
        this.date_time = date_time;
        this.likes = likes;
        this.owner = owner;
    }
}
