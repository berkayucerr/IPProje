package dao;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class Categorydao extends DBConnection {
    
     public void create(Category u){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into category (category_name) values ('"+u.getName()+"')");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
     public void update(Category u){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("update category set category_name='"+u.getName()+"' where category_id="+u.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
     
      public void delete(Category u){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from category where category_id="+u.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
      
      public List<Category> read(String arananTerim,int page,int pageSize){
             List<Category> list=new ArrayList<>();
               int start=(page-1)*pageSize;
        
            try {
            String query = "select * from category";

            if (arananTerim != null) {
                query += " where category_name like ? ";
            }

            query += " order by category_id asc limit ? offset ?";

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
            while (rs.next()) {
                Category temp = new Category(rs.getInt("category_id"),rs.getString("category_name"));
                list.add(temp);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         return list;
      }
     
       public List<Category> read(){
        List<Category> list=new ArrayList<>();
   
        try{
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from category order by category_id asc");
            while(rs.next()){
                Category temp=new Category(rs.getInt("category_id"),rs.getString("category_name"));
                list.add(temp);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         return list;
    }
      public int count(String arananTerim) {
        int count=0;
        try {
            String query = "select count(category_id) as category_count from category ";
            if (arananTerim != null) {
                query += "where category_name like ?";
            }
            PreparedStatement pst = connect().prepareStatement(query);

             if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("category_count");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
      
      public Category categoryBul(int id) {
        Category k = null;
        try {
            Statement st = connect().createStatement();
            ResultSet rs = st.executeQuery("select * from category where category_id=" + id);
            rs.next();

            k = new Category();
            k.setId(rs.getInt("category_id"));
            k.setName(rs.getString("category_name"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }
}
