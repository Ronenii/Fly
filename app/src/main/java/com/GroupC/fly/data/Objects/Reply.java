package com.GroupC.fly.data.Objects;

public class Reply extends PostBase {

    /* DATA MEMBERS */
    private String ownerOfPostBase;


    /* METHODS */
    public String getOwnerOfPostBase() {
        return ownerOfPostBase;
    }


    public void setOwnerOfPostBase(String ownerOfPostBase) {
        this.ownerOfPostBase = ownerOfPostBase;
    }


    /** IMPLEMENTATION OF ABSTRACT FUNCTIONS **/
    //TODO: this method will update the post in the post DB on firebase.
    //A virtual method that differs between MasterPost and Reply.
    public void updatePostInDB()
    {

    }


    //TODO: this method will add the post into the post DB on firebase.
    //A virtual method that differs between MasterPost and Reply.
    public void createPostInDB()
    {

    }


    //TODO: this method will delete the post from the post DB on firebase.
    //A virtual method that differs between MasterPost and Reply.
    public void deletePostInDB()
    {

    }
}
