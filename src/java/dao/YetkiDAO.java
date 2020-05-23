package dao;

import entity.User;
import entity.Yetki;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class YetkiDAO extends DBConnection {

    PreparedStatement pst = null;
    ResultSet rs = null;

    public User girisYap(String mail, String sifre) {
        User temp = null;
        try {
            pst = this.connect().prepareStatement("select * from user where mail=? and sifre=?");
            pst.setString(1, mail);
            pst.setString(2, sifre);
            rs = pst.executeQuery();

            while (rs.next()) {
                temp = new User();
                temp.setUser_id(rs.getInt("user_id"));
                temp.setAd(rs.getString("ad"));
                temp.setSoyad(rs.getString("soyad"));
                temp.setMail(rs.getString("mail"));
                temp.setYetki(this.bul(rs.getInt("yetki_id")));
                temp.setSifre(rs.getString("sifre"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }

    public Yetki bul(int id) {
        Yetki yt = null;
        try {

            PreparedStatement st = this.connect().prepareStatement("select * from yetki where yetki_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            yt = new Yetki();
            yt.setYetki_id(rs.getInt("yetki_id"));
            yt.setYetki(rs.getString("yetki"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yt;
    }

    public List<Yetki> getYetki() {
        List<Yetki> yetkiList = new ArrayList();

        try {
            PreparedStatement st = this.connect().prepareStatement("select * from yetki");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Yetki tmp = new Yetki();
                tmp.setYetki_id(rs.getInt("yetki_id"));
                tmp.setYetki(rs.getString("yetki"));
                yetkiList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yetkiList;
    }

    public void ekle(Yetki yetki) {
        try {

            PreparedStatement st = this.connect().prepareStatement("insert into yetki(yetki) values(?)");
            st.setString(1, yetki.getYetki());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Yetki yetki) {
        try {

            PreparedStatement st = this.connect().prepareStatement("delete from yetki where yetki_id=?");
            st.setInt(1, yetki.getYetki_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Yetki yetki) {
        try {

            PreparedStatement st = this.connect().prepareStatement("update yetki set yetki=? where yetki_id=?");
            st.setString(1, yetki.getYetki());
            st.setInt(2, yetki.getYetki_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
