package entity;

import java.util.Objects;

public class Platform {
    private int id;
    private String platform_name;

    public Platform(int id, String platform_name) {
        this.id = id;
        this.platform_name = platform_name;
    }

    public Platform() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.platform_name);
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
        final Platform other = (Platform) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.platform_name, other.platform_name)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
