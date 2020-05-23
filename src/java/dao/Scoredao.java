
package dao;

import util.DBConnection;
import entity.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class Scoredao extends DBConnection {
    private DBConnection connector;
    private Connection connection;   

    public void create(Score score) {
        try {
            PreparedStatement pst = connect().prepareStatement("insert into score (game_score) values(?)");
            pst.setString(1, score.getScore());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Score score) {

        try {
            PreparedStatement pst = connect().prepareStatement("delete from score where score_id=?");
            pst.setInt(1, score.getScore_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Score score) {

        try {
            PreparedStatement pst = connect().prepareStatement("update score set game_score=? where score_id=?");
            pst.setString(1, score.getScore());
            pst.setInt(2, score.getScore_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   public List<Score> read(){
        List<Score> list=new ArrayList<>();
        try{
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from score order by score_id asc");
            while(rs.next()){
                Score temp=new Score(rs.getInt("score_id"),rs.getString("game_score"));
                list.add(temp);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         return list;
    }
   public List<Score> read(String arananTerim,int page, int pageSize){
        List<Score> list = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            String query = "select * from score";

            if (arananTerim != null) {
                query += " where game_score like ? ";
            }
            query += " order by score_id asc limit ? offset ?";

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
                Score temp=new Score(rs.getInt("score_id"),rs.getString("game_score"));
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
            String query = "select count(score_id) as score_count from score ";
            if (arananTerim != null) {
                query += "where game_score like ?";
            }
            PreparedStatement pst = connect().prepareStatement(query);

             if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("score_count");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
   
   public Score scoreBul(int id) {
        Score k = null;
        try {
            Statement st = connect().createStatement();
            ResultSet rs = st.executeQuery("select * from score where score_id=" + id);
            rs.next();
            k = new Score();
            k.setScore_id(rs.getInt("score_id"));
            k.setScore(rs.getString("game_score"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }

}

