package dao;

import entity.Comment;
import entity.Game;
import entity.Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class Commentdao extends DBConnection {

    private Scoredao scoreDao;
    private Gamedao gameDao;
    public void create(Comment u) {
        try {
            PreparedStatement pst = connect().prepareStatement("insert into comments (comment,score_id,game_id) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, u.getComment());
            pst.setInt(2, u.getScore().getScore_id());
            pst.setInt(3, u.getGame().getGame_id());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Comment u) {
        try {
            PreparedStatement pst = connect().prepareStatement("update  comments set comment=?,score_id=?,game_id=? where comment_id=?");
            pst.setString(1, u.getComment());
            pst.setInt(2, u.getScore().getScore_id());
            pst.setInt(3, u.getGame().getGame_id());
            pst.setInt(4, u.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Comment u) {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from comments where comment_id=" + u.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Comment> read(String arananTerim, int page, int pageSize) {
        List<Comment> list = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            String query = "select * from comments";

            if (arananTerim != null) {
                query += " where comment like ? ";
            }
            query += " order by comment_id asc limit ? offset ?";

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
                Comment temp = new Comment(rs.getInt("comment_id"), rs.getString("comment"));
                temp.setScore(this.getScoreDao().scoreBul(rs.getInt("score_id")));
                temp.setGame(this.getGameDao().gameBul(rs.getInt("game_id")));
                list.add(temp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Comment> yorumgetir(Game g) {
        List<Comment> list = new ArrayList<>();

        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from comments where game_id=" + g.getGame_id());
            while (rs.next()) {
                Comment temp = new Comment(rs.getInt("comment_id"), rs.getString("comment"));
                temp.setScore(this.getScoreDao().scoreBul(rs.getInt("score_id")));
                temp.setGame(this.getGameDao().gameBul(rs.getInt("game_id")));
                list.add(temp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int count(String arananTerim) {
        int count=0;
        try {
            String query = "select count(comment_id) as comment_count from comments ";
            if (arananTerim != null) {
                query += "where comment like ?";
            }
            PreparedStatement pst = connect().prepareStatement(query);

             if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("comment_count");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public Gamedao getGameDao() {
        if(this.gameDao==null){
            this.gameDao=new Gamedao();
        }
        return gameDao;
    }

   

    public Scoredao getScoreDao() {
        if(this.scoreDao==null){
            this.scoreDao=new Scoredao();
        }
        return scoreDao;
    }


}
