package controller;

import dao.Scoredao;
import entity.Score;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ScoreController implements Serializable{
    
    private Scoredao dao;
    private Score entity;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String terim;
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
    public void create() {
        this.getDao().create(entity);
        this.entity=new Score();
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
          this.pageCount = (int) Math.ceil(this.getDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public List<Score> getRead() {
        return this.getDao().read(terim,page,pageSize);
    }
    
     public String islemIptal() {
        this.clearForm();
        return "/Admin/score/scores.xhtml";
    }

    public void clearForm() {
        this.entity = new Score();
    }

    
    public void updateForm(Score u) {
        this.entity = u;
    }

    public String delete() {
        this.getDao().delete(this.entity);
        this.clearForm();
        return "/Admin/score/scores.xhtml";
    }

    public String deleteForm(Score u) {
        this.entity = u;
        return "/Admin/score/deleteonay.xhtml";
    }

    public void update() {
        this.getDao().update(entity);
    }
    
    public Scoredao getDao() {
        if(this.dao==null){
            this.dao=new Scoredao();
        }
        return dao;
    }

    public void setDao(Scoredao dao) {
        this.dao = dao;
    }

    public Score getEntity() {
        if(this.entity==null){
            this.entity=new Score();
        }
        return entity;
    }

    public void setEntity(Score entity) {
        this.entity = entity;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }
    
}
