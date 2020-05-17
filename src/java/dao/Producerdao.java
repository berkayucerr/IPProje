package dao;

import entity.Producer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class Producerdao extends DBConnection {
    
    public void create(Producer p){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into producer (producer_name) values ('"+p.getProducer_name()+"')");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Producer p){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("update producer set producer_name='"+p.getProducer_name()+"' where producer_id="+p.getProducer_id());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Producer p){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from producer where producer_id="+p.getProducer_id());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<Producer> read(){
        List<Producer> list=new ArrayList<>();
        try{
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from producer order by producer_id asc");
            while(rs.next()){
                Producer temp=new Producer(rs.getInt("producer_id"),rs.getString("producer_name"));
                list.add(temp);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         return list;
    }
     public List<Producer> read(String arananTerim,int page, int pageSize){
        List<Producer> list = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            String query = "select * from producer";

            if (arananTerim != null) {
                query += " where producer_name like ? ";
            }
            query += " order by producer_id asc limit ? offset ?";

            PreparedStatement pst = this.connect().prepareStatement(query);

            if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
                pst.setInt(2, pageSize);
                pst.setInt(3, start);
            } else {
                pst.setInt(1, pageSize);
                pst.setInt(2, start);
            }
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Producer temp=new Producer(rs.getInt("producer_id"),rs.getString("producer_name"));
                list.add(temp);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         return list;
    }
       public int count() {
        int count=0;
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select count(producer_id) as producer_count from producer");
            rs.next();
            count=rs.getInt("producer_count");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    public Producer producerBul(int id) {
        Producer k = null;
        try {
            Statement st = connect().createStatement();
            ResultSet rs = st.executeQuery("select * from producer where producer_id=" + id);
            rs.next();

            k = new Producer();
            k.setProducer_id(rs.getInt("producer_id"));
            k.setProducer_name(rs.getString("producer_name"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }
   
}
