package dao;

import entity.Category;
import entity.Dosya;
import entity.Game;
import entity.Platform;
import entity.Producer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class Gamedao extends DBConnection {

    private Categorydao categoryDao;
    private Platformdao platformDao;
    private Producerdao producerDao;
    private DosyaDAO dosyaDao;

    public List<Game> read(String arananTerim,int page, int pageSize) {
        List<Game> list = new ArrayList<>();
        int baslangic = (page - 1) * pageSize;
        try {
            String query = "select * from game";

            if (arananTerim != null) {
                query += " where baslik like ? ";
            }

            query += " order by game_id asc limit ? offset ?";

            PreparedStatement pst = this.connect().prepareStatement(query);

            if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
                pst.setInt(2, pageSize);
                pst.setInt(3, baslangic);
            } else {
                pst.setInt(1, pageSize);
                pst.setInt(2, baslangic);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Game temp = new Game(rs.getInt("game_id"), rs.getString("baslik"), rs.getString("aciklama"), rs.getString("vizyon_tarihi"));
                temp.setProducer(this.getProducerDao().producerBul(rs.getInt("producer_id")));
                temp.setCategory(this.getCategoryDao().categoryBul(rs.getInt("category_id")));
                temp.setDosya(this.getDosyaDao().bul(rs.getInt("dosya_id")));
                temp.setGame_platform(this.getPlatformDao().getGamePlatforms(temp.getGame_id()));
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
            String query = "select count(game_id) as game_count from game ";
            if (arananTerim != null) {
                query += "where baslik like ?";
            }
            PreparedStatement pst = connect().prepareStatement(query);

             if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("game_count");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public List<Game> read() {
        List<Game> list = new ArrayList<>();
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from game order by game_id asc");
            while (rs.next()) {
                Game temp = new Game(rs.getInt("game_id"), rs.getString("baslik"), rs.getString("aciklama"), rs.getString("vizyon_tarihi"));
                temp.setProducer(this.getProducerDao().producerBul(rs.getInt("producer_id")));
                temp.setCategory(this.getCategoryDao().categoryBul(rs.getInt("category_id")));
                temp.setDosya(this.getDosyaDao().bul(rs.getInt("dosya_id")));
                temp.setGame_platform(this.getPlatformDao().getGamePlatforms(temp.getGame_id()));
                list.add(temp);
            }
            System.out.println(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    //Kategoriye göre oyun getiriyor dolayısıyla gamedao içinde bulunuyor
    public List<Game> categoryrankingBul(Game id) {
        
        List<Game> gamelist = new ArrayList<>();
        try {
            Statement st = connect().createStatement();
            ResultSet rs = st.executeQuery("select * from game where category_id=" + id.getCategory().getId());
            
            while (rs.next()) {
                Game temp = new Game(rs.getInt("game_id"), rs.getString("baslik"), rs.getString("aciklama"), rs.getString("vizyon_tarihi"));
                temp.setProducer(this.getProducerDao().producerBul(rs.getInt("producer_id")));
                temp.setCategory(this.getCategoryDao().categoryBul(rs.getInt("category_id")));
                temp.setDosya(this.getDosyaDao().bul(rs.getInt("dosya_id")));
                temp.setGame_platform(this.getPlatformDao().getGamePlatforms(temp.getGame_id()));
                gamelist.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gamelist;
    }

    public void update(Game game) {
        try {
            PreparedStatement pst = connect().prepareStatement("update game set baslik=?,aciklama=?,vizyon_tarihi=?,category_id=?,producer_id=?,dosya_id=? where game_id=?");
            pst.setString(1, game.getBaslik());
            pst.setString(2, game.getAciklama());
            pst.setString(3, game.getVizyon_tarihi());
            pst.setInt(4, game.getCategory().getId());
            pst.setInt(5, game.getProducer().getProducer_id());
            pst.setInt(6, game.getDosya().getDosya_id());
            pst.setInt(7, game.getGame_id());
            pst.executeUpdate();

            pst = this.connect().prepareStatement("delete from game_platform where game_id=?");
            pst.setInt(1, game.getGame_id());
            pst.executeUpdate();

            for (Platform k : game.getGame_platform()) {
                pst = this.connect().prepareStatement("insert into game_platform(game_id,platform_id) values(?,?)");
                pst.setInt(1, game.getGame_id());
                pst.setInt(2, k.getId());
                pst.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Game game) {
        try {
            PreparedStatement pst = connect().prepareStatement("insert into game (baslik,aciklama,vizyon_tarihi,category_id,producer_id,dosya_id) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, game.getBaslik());
            pst.setString(2, game.getAciklama());
            pst.setString(3, game.getVizyon_tarihi());
            pst.setInt(4, game.getCategory().getId());
            pst.setInt(5, game.getProducer().getProducer_id());
            pst.setInt(6, game.getDosya().getDosya_id());
            pst.executeUpdate();

            ResultSet gk = pst.getGeneratedKeys();
            Integer game_id = null;
            if (gk.next()) {
                game_id = gk.getInt(1);
            }

            for (Platform k : game.getGame_platform()) {
                pst = this.connect().prepareStatement("insert into game_platform(game_id,platform_id) values(?,?)");
                pst.setInt(1, game_id);
                pst.setInt(2, k.getId());
                pst.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Game game) {
        try {
            PreparedStatement pst = connect().prepareStatement("delete from game where game_id=?");
            pst.setInt(1, game.getGame_id());
            pst.executeUpdate();

            pst = this.connect().prepareStatement("delete from game_platform where game_id=?");
            pst.setInt(1, game.getGame_id());
            pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    public Categorydao getCategoryDao() {
        if (categoryDao == null) {
            this.categoryDao = new Categorydao();
        }
        return categoryDao;
    }

    public Producerdao getProducerDao() {
        if (this.producerDao == null) {
            this.producerDao = new Producerdao();
        }
        return producerDao;
    }

    public Platformdao getPlatformDao() {
        if (this.platformDao == null) {
            this.platformDao = new Platformdao();
        }
        return platformDao;
    }

    public DosyaDAO getDosyaDao() {
        if(this.dosyaDao==null){
            this.dosyaDao=new DosyaDAO();
        }
        return dosyaDao;
    }

    
}
