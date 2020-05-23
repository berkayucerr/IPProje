
package controller;

import dao.Platformdao;
import entity.Platform;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PlatformController implements Serializable{
    private Platformdao dao;
    private Platform entity;
    private List<Platform> pList;
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
    public void create() {
        this.getDao().create(entity);
        this.clearForm();
    }

    public List<Platform> getpList() {
        return pList;
    }

    public void setpList(List<Platform> pList) {
        this.pList = pList;
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
    public List<Platform> getRead() {
        return this.getDao().read(this.getTerim(),page,pageSize);
    }
    public void updateForm(Platform u){
        this.entity = u;
        
    }
    public void update() {
        this.getDao().update(entity);
        this.clearForm();
        
    }
     public String islemIptal() {
        this.clearForm();
        return "/Admin/platform/platform.xhtml";
    }

    public void clearForm() {
        this.entity = new Platform();
    }
    public String delete() {
        this.getDao().delete(this.entity);
        return "/Admin/platform/platform.xhtml";
    }
    
    public String deleteForm(Platform p) {
        this.entity=p;
        return "/Admin/platform/deleteonay.xhtml";
    }
    public Platformdao getDao() {
        if(this.dao==null){
            this.dao=new Platformdao();
        }
        return dao;
    }

    public List<Platform> getPList() {
        pList=this.getDao().read();
        return pList;
    }

    public void setPList(List<Platform> pList) {
        this.pList = pList;
    }

    public void setDao(Platformdao dao) {
        this.dao = dao;
    }

    public Platform getEntity() {
        if(this.entity==null){
            entity=new Platform();
        }
        return entity;
    }

    public void setEntity(Platform entity) {
        this.entity = entity;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }
    
    
}