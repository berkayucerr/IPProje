package entity;

import java.util.Objects;

public class Yetki {
    private int yetki_id;
    private String yetki;

    public Yetki() {
    }

    public Yetki(int yetki_id, String yetki) {
        this.yetki_id = yetki_id;
        this.yetki = yetki;
    }

    public int getYetki_id() {
        return yetki_id;
    }

    public void setYetki_id(int yetki_id) {
        this.yetki_id = yetki_id;
    }

    public String getYetki() {
        return yetki;
    }

    public void setYetki(String yetki) {
        this.yetki = yetki;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.yetki_id;
        hash = 13 * hash + Objects.hashCode(this.yetki);
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
        final Yetki other = (Yetki) obj;
        if (this.yetki_id != other.yetki_id) {
            return false;
        }
        if (!Objects.equals(this.yetki, other.yetki)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yetki{" + "yetki_id=" + yetki_id + ", yetki=" + yetki + '}';
    }

    
}
