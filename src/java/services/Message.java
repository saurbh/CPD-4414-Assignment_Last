/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import javax.json.Json;
import java.util.Date;
import javax.json.JsonObject;

/**
 *
 * @author Saurabh
 */
public class Message {
    int id;
    String title;
    String contents;
    String author;
    Date date;

    public Message(int id, String title, String contents, String author, Date date) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.date = date;
    }

    public Message() {
    }

    public Message(JsonObject json){
       // id = ;
        title = json.getString("title");
        contents = json.getString("contents");
        author = json.getString("author");
        date =new Date();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public JsonObject toJSON(){
        JsonObject json = Json.createObjectBuilder()
                .add("ID", id)
                .add("TITLE",title )
                .add("CONTENTS", contents)
                .add("AUTHOR", author)
                .add("DATE", date.toString())
                .build();
        return json;
    }
}
