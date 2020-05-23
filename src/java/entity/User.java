package entity;

import java.util.Objects;

public class User {

    private int user_id;
    private String ad;
    private String soyad;
    private String mail;
    private Yetki yetki;
    private String sifre;

    public User(int uye_id, String uye_ad, String uye_soyad, String uye_mail, Yetki yetki, String sifre) {
        this.user_id = uye_id;
        this.ad = uye_ad;
        this.soyad = uye_soyad;
        this.mail = uye_mail;
        this.yetki = yetki;
        this.sifre = sifre;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Yetki getYetki() {
        if(this.yetki==null){
            this.yetki=new Yetki();
        }
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.user_id;
        hash = 41 * hash + Objects.hashCode(this.ad);
        hash = 41 * hash + Objects.hashCode(this.soyad);
        hash = 41 * hash + Objects.hashCode(this.mail);
        hash = 41 * hash + Objects.hashCode(this.yetki);
        hash = 41 * hash + Objects.hashCode(this.sifre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.ad, other.ad)) {
            return false;
        }
        if (!Objects.equals(this.soyad, other.soyad)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.sifre, other.sifre)) {
            return false;
        }
        if (!Objects.equals(this.yetki, other.yetki)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", ad=" + ad + ", soyad=" + soyad + ", mail=" + mail + ", yetki=" + yetki + ", sifre=" + sifre + '}';
    }

    

    


}
