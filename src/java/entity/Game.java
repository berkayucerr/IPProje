package entity;

import java.util.List;
import java.util.Objects;



public class Game {

    private int game_id;
    private String baslik;
    private String aciklama;
    private String vizyon_tarihi;
    private Producer producer;
    private Category category;
    private List<Platform> game_platform;
    private List<Comment> gamecommentlist;
    private Dosya dosya;
    public Game() {
        
    }

    public Game(int game_id, String baslik, String aciklama, String vizyon_tarihi) {
        this.game_id = game_id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.vizyon_tarihi = vizyon_tarihi;
    }

    public int getGame_id() {
        return game_id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getGamecommentlist() {
        return gamecommentlist;
    }

    public void setGamecommentlist(List<Comment> gamecommentlist) {
        this.gamecommentlist = gamecommentlist;
    }

    public Dosya getDosya() {
        if(this.dosya==null){
            this.dosya=new Dosya();
        }
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

   

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }


    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Game{" + "game_id=" + game_id + ", baslik=" + baslik + ", aciklama=" + aciklama + ", vizyon_tarihi=" + vizyon_tarihi + ", producer=" + producer + ", category=" + category + ", game_platform=" + game_platform + ", dosya=" + dosya + '}';
    }

   

    public List<Platform> getGame_platform() {
        return game_platform;
    }

    public void setGame_platform(List<Platform> game_platform) {
        this.game_platform = game_platform;
    }

    public String getVizyon_tarihi() {
        return vizyon_tarihi;
    }

    public void setVizyon_tarihi(String vizyon_tarihi) {
        this.vizyon_tarihi = vizyon_tarihi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.game_id;
        hash = 47 * hash + Objects.hashCode(this.baslik);
        hash = 47 * hash + Objects.hashCode(this.aciklama);
        hash = 47 * hash + Objects.hashCode(this.vizyon_tarihi);
        hash = 47 * hash + Objects.hashCode(this.producer);
        hash = 47 * hash + Objects.hashCode(this.category);
        hash = 47 * hash + Objects.hashCode(this.game_platform);
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
        final Game other = (Game) obj;
        if (this.game_id != other.game_id) {
            return false;
        }
        if (!Objects.equals(this.baslik, other.baslik)) {
            return false;
        }
        if (!Objects.equals(this.aciklama, other.aciklama)) {
            return false;
        }
        if (!Objects.equals(this.vizyon_tarihi, other.vizyon_tarihi)) {
            return false;
        }
        if (!Objects.equals(this.producer, other.producer)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.game_platform, other.game_platform)) {
            return false;
        }
        return true;
    }

    
  

    

   


}
