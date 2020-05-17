package dao;

import entity.Dosya;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class DosyaDAO extends DBConnection{

   
    
    public List<Dosya> findAll() {
        List<Dosya> dosyaList = new ArrayList<>();
        try{
            PreparedStatement pst = connect().prepareStatement("select * from dosya");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Dosya d = new Dosya();
                d.setDosya_id(rs.getInt("dosya_id"));
                d.setDosya_ismi(rs.getString("dosya_ismi"));
                d.setDosya_konumu(rs.getString("dosya_konumu"));
                d.setDosya_tipi(rs.getString("dosya_tipi"));
                
                dosyaList.add(d);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return dosyaList;
    }
    public List<Dosya> findAll(String arananTerim,int sayfa, int sayfaBoyutu) {
        List<Dosya> dosyaList = new ArrayList<>();
         int baslangic=(sayfa-1)*sayfaBoyutu;
        
        try {
            String query = "select * from dosya";
            
            if(arananTerim!= null){
                query += " where dosya_ismi like ? ";
            }
            
            query+= " order by dosya_id asc limit ? offset ?";
            
            PreparedStatement pst = this.connect().prepareStatement(query);
            if(arananTerim!=null){
                pst.setString(1, "%"+arananTerim+"%");
                pst.setInt(2, sayfaBoyutu);
                pst.setInt(3, baslangic);
            }
            else{
                pst.setInt(1, sayfaBoyutu);
                pst.setInt(2, baslangic);
            }        
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Dosya d = new Dosya();
                d.setDosya_id(rs.getInt("dosya_id"));
                d.setDosya_ismi(rs.getString("dosya_ismi"));
                d.setDosya_konumu(rs.getString("dosya_konumu"));
                d.setDosya_tipi(rs.getString("dosya_tipi"));
                
                dosyaList.add(d);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return dosyaList;
    }
      public int count() {
        int count=0;
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select count(dosya_id) as dosya_count from dosya");
            rs.next();
            count=rs.getInt("dosya_count");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
    public Dosya bul(int id) {
        Dosya ds = null;
        try {
            PreparedStatement st = connect().prepareStatement("select * from dosya where dosya_id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            ds = new Dosya();
            ds.setDosya_id(rs.getInt("dosya_id"));
            ds.setDosya_ismi(rs.getString("dosya_ismi"));
            ds.setDosya_konumu(rs.getString("dosya_konumu"));
            ds.setDosya_tipi(rs.getString("dosya_tipi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ds;
    }

     public void ekle(Dosya d){
        
        try{
            PreparedStatement pst = this.connect().prepareStatement("insert into dosya (dosya_ismi,dosya_konumu,dosya_tipi) values(?,?,?)");
            pst.setString(1, d.getDosya_ismi());
            pst.setString(2, d.getDosya_konumu());
            pst.setString(3, d.getDosya_tipi());
            pst.executeUpdate();            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public int sayi(String arananTerim) {
        int sayi=0;
        try {
            String query = "select count(dosya_id) as dosya_sayisi from dosya ";
            if(arananTerim!=null){
                query += "where dosya_ismi like ?";
            }
            PreparedStatement st = connect().prepareStatement(query);
            
            if(arananTerim !=null){
                st.setString(1, "%"+arananTerim+"%");
            }
            
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi=rs.getInt("dosya_sayisi");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }
    
}
