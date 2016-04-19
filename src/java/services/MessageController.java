/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.json.Json;
import javax.json.stream.JsonParser;

/**
 *
 * @author Saurabh
 */
@ApplicationScoped
public class MessageController {
    public static List<Message> mes  
        = new ArrayList(Arrays.asList(new Message(1,"Java", "Assignments","Saurabh",new Date("2016-04-19")),
                                      new Message(2,"JQuery", "Term roject","Shagan",new Date("2016-04-15")),
                                      new Message(3,"PL/SQL", " Database PL/SQL","Gurpreet",new Date("2016-04-21")),
                                      new Message(4,"Database", "Database Design","Jatinder",new Date("2015-02-25"))));
//    Date d = new Date();
//    
//    
//    //mes = new ArrayList<>();
    
    
    public MessageController() {
    }

   
    public void add(Message m){
        mes.add(m);
    }
    
    public void edit(int id, Message msg){
        Message message = getById(id);
        remove(id);
        message = msg;
        
    }
    public void remove(int id){
        Message message = getById(id);
        mes.remove(message);
    }
    public Message getById(int id){
        for (Message p : mes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public List<Message> getFromTo(Date from, Date to){
        List<Message> messages = new ArrayList<>();

       // Date senttime;
        
        for (Message m : mes) {
                if(m.getDate().after(from) && m.getDate().before(to)){
                    messages.add(m);
                }           
        }
        return messages;
    }
    
    public List<Message> getAll(){
        return mes;
    }
    
    
    
}
