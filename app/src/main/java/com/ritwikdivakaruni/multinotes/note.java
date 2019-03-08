package com.ritwikdivakaruni.multinotes;
import java.io.Serializable;


public class note implements Serializable{
    private String title;
    private String content;
    private String updateTime;

    /* This constructor can only be used when a new note
    is being created
     */
    public note(String title, String content, String updateTime) {
        this.title = title;
        this.content = content;
        this.updateTime = updateTime;
    }

    /* Individual set and get functions */
    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public String getUpdateTime(){
        return this.updateTime;
    }

}
