package dao;

import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                temp.setAdmin(rs.getInt("admin"));
                temp.setSifre(rs.getString("sifre"));
            }
            }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            return temp;
        }
    }
