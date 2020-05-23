package controller;

import dao.Categorydao;
import dao.Commentdao;
import dao.DosyaDAO;
import dao.Gamedao;
import dao.Platformdao;
import dao.Producerdao;
import entity.Category;
import entity.Comment;
import entity.Dosya;
import entity.Game;
import entity.Platform;
import entity.Producer;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
@Named
@SessionScoped
public class GameController implements Serializable {

    private Game entity;
    private List<Game> gamelist;
    private List<Game> categoryranking;
    private List<Platform> platformlist;
    private Gamedao dao;
    private List<Category> categorylist;
    private List<Producer> producerlist;
    private List<Comment> commentList;
    private List<Dosya> dosyalist;
    private Categorydao categorydao;
    private Producerdao producerdao;
    private Platformdao platformdao;
    private Commentdao commentdao;
    private DosyaDAO dosyadao;
    private String terim;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    
    @Inject
    private CommentController cc;
    
    public void ara(){
        this.setPage(1);
    }
    public void create() {
        this.getDao().create(entity);
        this.clearForm();
    }

    public void updateForm(Game u) {
        this.entity = u;
    }

    public String update() {
        this.getDao().update(entity);
        this.clearForm();
        return "/Admin/game/Game";
    }

    public List<Game> getRead() {
        return this.getDao().read(this.getTerim(),page, pageSize);
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }

    public String delete() {
        this.getDao().delete(this.entity);
        this.clearForm();
        return "/Admin/game/Game.xhtml";
    }
    
    public String islemIptal() {
        this.clearForm();
        return "/Admin/game/Game.xhtml";
    }
    public void clearForm() {
        this.entity = new Game();
    }
   
    public String deleteForm(Game g) {
        this.entity=g;
        return "/Admin/game/deleteonay";
    }
    

    public void next() {
        if(this.page==this.getPageCount())
            this.page=1;
        else
        this.page++;
    }
     public void previous() {
        if(this.page==1)
            this.page=this.getPageCount();
        else
        this.page--;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDao().count(this.getTerim()) / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public CommentController getCc() {
        return cc;
    }
    public void setCc(CommentController cc) {
        this.cc = cc;
    }
    public String oyunsec(Game g){
        this.entity=g;
        return "publiccomment.xhtml";
    }
    public void getxYorumekle(){
        this.getCc().yorumekle(entity);
        
    }
    public List<Comment> getYorumGetir(){
       commentList= this.getCc().yorumgetir(entity);
       return commentList;
    }
    public String yorumlar(Game g){
        this.setGame(g);
        return "publiccomment.xhtml";
    }
    
    public Game getEntity() {
        if(this.entity==null){
            this.entity=new Game();
        }
        return entity;
    }

    public Commentdao getCommentdao() {
        if(this.commentdao==null){
            this.commentdao=new Commentdao();
        }
        return commentdao;
    }

    public void setCommentdao(Commentdao commentdao) {
        this.commentdao = commentdao;
    }

    
    public void setEntity(Game entity) {
        this.entity = entity;
    }

    public List<Game> getCategoryranking() {
        this.categoryranking=this.getDao().categoryrankingBul(this.entity);
        System.out.println(this.categoryranking);
        return categoryranking;
    }

    public void setCategoryranking(List<Game> categoryranking) {
        this.categoryranking = categoryranking;
    }

   
    
    public Categorydao getCategorydao() {
        if (this.categorydao == null) {
            this.categorydao = new Categorydao();
        }
        return categorydao;
    }

    public void setCategorydao(Categorydao categorydao) {
        this.categorydao = categorydao;
    }

    public Producerdao getProducerdao() {
        if (this.producerdao == null) {
            this.producerdao = new Producerdao();
        }
        return producerdao;
    }

    public List<Dosya> getDosyalist() {
        this.dosyalist=this.getDosyadao().findAll();
        return dosyalist;
    }

    public void setDosyalist(List<Dosya> dosyalist) {
        this.dosyalist = dosyalist;
    }
    
    

    public DosyaDAO getDosyadao() {
        if(this.dosyadao==null){
            this.dosyadao=new DosyaDAO();
        }
        return dosyadao;
    }

    public void setDosyadao(DosyaDAO dosyadao) {
        this.dosyadao = dosyadao;
    }

    public Platformdao getPlatformdao() {
        if (this.platformdao == null) {
            this.platformdao = new Platformdao();
        }
        return platformdao;
    }

    public void setPlatformdao(Platformdao platformdao) {
        this.platformdao = platformdao;
    }

    public void setProducerdao(Producerdao producerdao) {
        this.producerdao = producerdao;
    }
    
    public List<Category> getCategorylist() {
        categorylist = this.getCategorydao().read();
        return categorylist;
    }

    public List<Producer> getProducerlist() {
        producerlist = this.getProducerdao().read();
        return producerlist;
    }

    public List<Platform> getPlatformlist() {
        platformlist = this.getPlatformdao().read();
        return platformlist;
    }

    public void setPlatformlist(List<Platform> platformlist) {
        this.platformlist = platformlist;
    }

    public Game getGame() {
        if (this.entity == null) {
            this.entity = new Game();
        }
        return entity;
    }

    public void setGame(Game game) {
        this.entity = game;
    }

    public List<Game> getGamelist() {
        return gamelist;
    }

    public void setGamelist(List<Game> gamelist) {
        this.gamelist = gamelist;
    }

    public Gamedao getDao() {
        if (this.dao == null) {
            this.dao = new Gamedao();
        }
        return dao;
    }

    public void setDao(Gamedao dao) {
        this.dao = dao;
    }

    

    public Game getFilm() {
        if (this.entity == null) {
            this.entity = new Game();
        }
        return entity;
    }

    public void setFilm(Game film) {
        this.entity = film;
    }

    public void setFilmList(List<Game> gamelist) {
        this.gamelist = gamelist;
    }

    public Gamedao getFilmDao() {
        if (this.dao == null) {
            this.dao = new Gamedao();
        }
        return dao;
    }

    public void setFilmDao(Gamedao gamedao) {
        this.dao = gamedao;
    }

}
