package dao;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class Userdao extends DBConnection {

    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private YetkiDAO yetkidao;

    public void insert(User uye) {
        try {
            pst = this.connect().prepareStatement("insert into user (ad,soyad,mail,yetki_id,sifre) values (?,?,?,?,?)");
            pst.setString(1, uye.getAd());
            pst.setString(2, uye.getSoyad());
            pst.setString(3, uye.getMail());
            pst.setInt(4, uye.getYetki().getYetki_id());
            pst.setString(5, uye.getSifre());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("UyeDAO HATA(Create) : " + ex.getMessage());
        }

    }

    public void delete(User uye) {
        try {
            pst = this.connect().prepareStatement("delete from user where user_id=?");
            pst.setInt(1, uye.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(" UyeDAO HATA(Delete): " + ex.getMessage());
        }
    }

   public List<User> getKullanici(String arananTerim,int sayfa, int sayfaBoyutu) {
        List<User> kullaniciList = new ArrayList();
        int baslangic = (sayfa - 1) * sayfaBoyutu;
        try {
            String query = "select * from user";

            if (arananTerim != null) {
                query += " where ad like ? ";
            }
            query += "order by user_id asc limit ? offset ?";
            PreparedStatement st = this.connect().prepareStatement(query);
            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
                st.setInt(2, sayfaBoyutu);
                st.setInt(3, baslangic);
            } else {
                st.setInt(1, sayfaBoyutu);
                st.setInt(2, baslangic);
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                
                User tmp = new User(rs.getInt("user_id"),rs.getString("ad"),rs.getString("soyad"), rs.getString("mail"),this.getYetkidao().bul(rs.getInt("yetki_id")),rs.getString("sifre"));
                kullaniciList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kullaniciList;
    }

    public void update(User uye) {
        try {
            pst = this.connect().prepareStatement("update user set ad=?,soyad=?,mail=?,yetki_id=?,sifre=? where user_id=?");
            pst.setString(1, uye.getAd());
            pst.setString(2, uye.getSoyad());
            pst.setString(3, uye.getMail());
            pst.setInt(4, uye.getYetki().getYetki_id());
            pst.setString(5, uye.getSifre());
            pst.setInt(6, uye.getUser_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" UyeDAO HATA(Update): " + ex.getMessage());
        }
    }

    public int count(String arananTerim) {
        int count=0;
        try {
            String query = "select count(user_id) as user_count from user ";
            if (arananTerim != null) {
                query += "where ad like ?";
            }
            PreparedStatement pst = connect().prepareStatement(query);

             if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("user_count");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

  
    public void Kayitol(User user) {
        try {
            pst = this.connect().prepareStatement("insert into user (ad,soyad,mail,sifre) values (?,?,?,?)");
            pst.setString(1, user.getAd());
            pst.setString(2, user.getSoyad());
            pst.setString(3, user.getMail());
            pst.setString(4, user.getSifre());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("UyeDAO HATA(Create) : " + ex.getMessage());
        }
    }

    public YetkiDAO getYetkidao() {
        if(this.yetkidao==null){
            this.yetkidao=new YetkiDAO();
        }
        return yetkidao;
    }

    public void setYetkidao(YetkiDAO yetkidao) {
        this.yetkidao = yetkidao;
    }

}
