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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Connection conn;

        try {
            conn = utils.getConnection();
            java.sql.Date sqlDate = new java.sql.Date(m.getDate().getTime());
            PreparedStatement pst = conn.prepareStatement("Insert into message(id, title, contents, author, date) values(?,?,?,?,?)");

            pst.setInt(1, m.getId());
            pst.setString(2, m.getTitle());
            pst.setString(3, m.getContents());
            pst.setString(4, m.getAuthor());
            pst.setDate(5, sqlDate);
            
             pst.executeUpdate();
            getAll();

        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
         try {
            Connection conn =utils.getConnection();
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM message");

            while (rs.next()) {
                Message message = new Message(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("author"),
                        rs.getDate("date"));
                mes.add(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mes;
    }
    
    
    
}
