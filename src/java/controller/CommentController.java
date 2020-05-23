package controller;
import dao.Commentdao;
import dao.Gamedao;
import dao.Scoredao;
import entity.Comment;
import entity.Game;
import entity.Score;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named
@SessionScoped
public class CommentController implements Serializable{
    private Commentdao dao;
    private Comment entity;
    private List<Score> scorelist;
    private Scoredao scoredao;
    private Gamedao gamedao;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String terim;
    public void ara(){
        this.setPage(1);
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
    
     public void yorumekle(Game g) {
        this.entity.setGame(g);
        this.getDao().create(entity);
        clearForm();
    }
     
     public List<Comment> yorumgetir(Game g) {
        return this.getDao().yorumgetir(g);
    }
     
    public List<Comment> getRead() {
        return this.getDao().read(this.getTerim(),page, pageSize);
    }
    public void updateForm(Comment u){
        this.entity = u;
        
    }
    public void update() {
        this.getDao().update(entity);
        clearForm();        
    }
    public String islemIptal() {
        this.clearForm();
        return "/Admin/comment/comments.xhtml";
    }
    public void clearForm() {
        this.entity = new Comment();
    }
    public String delete() {
        this.getDao().delete(this.entity);
        this.clearForm();
        return "/Admin/comment/comments";
    }
    public String deleteForm(Comment u) {
        this.entity=u;
        return "/Admin/comment/deleteonay";
    }
    
    public Commentdao getDao() {
        if(this.dao==null){
            this.dao=new Commentdao();
        }
        return dao;
    }

    public Scoredao getScoredao() {
        if(this.scoredao==null){
            this.scoredao=new Scoredao();
        }
        return scoredao;
    }

    public void setScoredao(Scoredao scoredao) {
        this.scoredao = scoredao;
    }

    public List<Score> getScorelist() {
        this.scorelist=this.getScoredao().read();
        return scorelist;
    }

    public void setScorelist(List<Score> scorelist) {
        this.scorelist = scorelist;
    }

    public void setDao(Commentdao dao) {
        this.dao = dao;
    }

    public Comment getEntity() {
        if(this.entity==null){
            this.entity=new Comment();
        }
        return entity;
    }

    public Gamedao getGamedao() {
        if(this.gamedao==null){
            this.gamedao=new Gamedao();
        }
        return gamedao;
    }

    public void setGamedao(Gamedao gamedao) {
        this.gamedao = gamedao;
    }

    public void setEntity(Comment entity) {
        this.entity = entity;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }
    
}
