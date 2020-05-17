package entity;

import java.util.Objects;

public class User {

    private int user_id;
    private String ad;
    private String soyad;
    private String mail;
    private int admin;
    private String sifre;

    public User(int uye_id, String uye_ad, String uye_soyad, String uye_mail, int admin, String sifre) {
        this.user_id = uye_id;
        this.ad = uye_ad;
        this.soyad = uye_soyad;
        this.mail = uye_mail;
        this.admin = admin;
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

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
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
        hash = 83 * hash + this.user_id;
        hash = 83 * hash + Objects.hashCode(this.ad);
        hash = 83 * hash + Objects.hashCode(this.soyad);
        hash = 83 * hash + Objects.hashCode(this.mail);
        hash = 83 * hash + this.admin;
        hash = 83 * hash + Objects.hashCode(this.sifre);
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
        if (this.admin != other.admin) {
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
        return true;
    }

    


}
