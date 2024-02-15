package com.example.demoprojectandroid.APICall.APIClient;

/**
 * Created by Kushal Prajapati on 3 May 2023.
 */
public class HttpParams {


    //public static final String APPURL="https://jsonplaceholder.typicode.com/";
    public static final String APPURL="https://api.escuelajs.co/api/v1/files/";

    public enum Type {
        OTHERS,
        REQUEST,
        REMINDER
    }

    //URLS
    public static final String getPosts= APPURL+"posts";
    public static final String domultipartApi= APPURL+"upload";


}


