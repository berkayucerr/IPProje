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
                temp.setScore(scoreBul(rs.getInt("score_id")));
                temp.setGame(gameBul(rs.getInt("game_id")));
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
                temp.setScore(scoreBul(rs.getInt("score_id")));
                temp.setGame(gameBul(rs.getInt("game_id")));
                list.add(temp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int count() {
        int count = 0;
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select count(comment_id) as comment_count from comments");
            rs.next();
            count = rs.getInt("comment_count");
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
            k.setScore(rs.getInt("game_score"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }

    public Game gameBul(int id) {
        Game k = null;
        try {
            Statement st = connect().createStatement();
            ResultSet rs = st.executeQuery("select * from game where game_id=" + id);
            rs.next();
            k = new Game();
            k.setGame_id(rs.getInt("game_id"));
            k.setBaslik(rs.getString("baslik"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }

}
