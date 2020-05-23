package dao;

import entity.Platform;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

public class Platformdao extends DBConnection {

    public void create(Platform u){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into platform (platform_name) values ('"+u.getPlatform_name()+"')");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void update(Platform u){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("update platform set platform_name='"+u.getPlatform_name()+"' where platform_id="+u.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(Platform u){
        try{
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from platform where platform_id="+u.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<Platform> read(){
        List<Platform> list=new ArrayList<>();
   
        try{
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from platform order by platform_id asc");
            while(rs.next()){
                Platform temp=new Platform(rs.getInt("platform_id"),rs.getString("platform_name"));
                list.add(temp);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         return list;
    }
     public List<Platform> read(String arananTerim,int page,int pageSize){
        List<Platform> list = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            String query = "select * from platform";

            if (arananTerim != null) {
                query += " where platform_name like ? ";
            }
            query += " order by platform_id asc limit ? offset ?";

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
                Platform temp=new Platform(rs.getInt("platform_id"),rs.getString("platform_name"));
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
            String query = "select count(platform_id) as platform_count from platform ";
            if (arananTerim != null) {
                query += "where platform_name like ?";
            }
            PreparedStatement pst = connect().prepareStatement(query);

             if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("platform_count");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
     public List<Platform> getGamePlatforms(int game_id) {
        List<Platform> gamePlatforms = new ArrayList<>();

        try {
            PreparedStatement st = this.connect().prepareStatement("select * from game_platform where game_id=?");
            st.setInt(1, game_id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                gamePlatforms.add(this.platformBul(rs.getInt("platform_id")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gamePlatforms;
    }
     public Platform platformBul(int id) {
        Platform k = null;
        try {
            Statement st = connect().createStatement();
            ResultSet rs = st.executeQuery("select * from platform where platform_id=" + id);
            rs.next();

            k = new Platform();
            k.setId(rs.getInt("platform_id"));
            k.setPlatform_name(rs.getString("platform_name"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }
    
}
