package com.GroupC.fly.data.Objects;

public class Reply extends Post{

    /* DATA MEMBERS */
    private String m_ownerOfMasterPost;

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

    /** METHODS **/
    public String get_OwnerOfMasterPost() {
        return m_ownerOfMasterPost;
    }

    public void set_OwnerOfMasterPost(String i_ownerOfMasterPost) {
        this.m_ownerOfMasterPost = i_ownerOfMasterPost;
    }


}
