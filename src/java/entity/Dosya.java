
package entity;

import java.util.Objects;

public class Dosya {
    private int dosya_id;
    private String dosya_konumu;
    private String dosya_ismi;
    private String dosya_tipi;

    public Dosya() {
    }

    public Dosya(int dosya_id, String dosya_konumu, String dosya_ismi, String dosya_tipi) {
        this.dosya_id = dosya_id;
        this.dosya_konumu = dosya_konumu;
        this.dosya_ismi = dosya_ismi;
        this.dosya_tipi = dosya_tipi;
    }

    public int getDosya_id() {
        return dosya_id;
    }

    public void setDosya_id(int dosya_id) {
        this.dosya_id = dosya_id;
    }

    public String getDosya_konumu() {
        return dosya_konumu;
    }

    public void setDosya_konumu(String dosya_konumu) {
        this.dosya_konumu = dosya_konumu;
    }

    public String getDosya_ismi() {
        return dosya_ismi;
    }

    public void setDosya_ismi(String dosya_ismi) {
        this.dosya_ismi = dosya_ismi;
    }

    public String getDosya_tipi() {
        return dosya_tipi;
    }

    public void setDosya_tipi(String dosya_tipi) {
        this.dosya_tipi = dosya_tipi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.dosya_id;
        hash = 59 * hash + Objects.hashCode(this.dosya_konumu);
        hash = 59 * hash + Objects.hashCode(this.dosya_ismi);
        hash = 59 * hash + Objects.hashCode(this.dosya_tipi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dosya other = (Dosya) obj;
        if (this.dosya_id != other.dosya_id) {
            return false;
        }
        if (!Objects.equals(this.dosya_konumu, other.dosya_konumu)) {
            return false;
        }
        if (!Objects.equals(this.dosya_ismi, other.dosya_ismi)) {
            return false;
        }
        if (!Objects.equals(this.dosya_tipi, other.dosya_tipi)) {
            return false;
        }
        return true;
    }

}
