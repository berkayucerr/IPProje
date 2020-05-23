package controller;

import dao.Categorydao;
import entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    private Categorydao dao;
    private Category entity;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String terim;
    
    public void ara(){
        this.setPage(1);
    }
    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
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

    public CategoryController() {

    }

    public String islemIptal() {
        this.clearForm();
        return "/Admin/category/categories.xhtml";
    }

    public void clearForm() {
        this.entity = new Category();
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Category();
    }

    public List<Category> getRead() {
        return this.getDao().read(this.getTerim(),page, pageSize);
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }

    public void updateForm(Category u) {
        this.entity = u;
    }

    public void update() {
        this.getDao().update(entity);
        this.clearForm();
    }

    public String delete() {
        this.getDao().delete(this.entity);
        this.clearForm();
        return "/Admin/category/categories.xhtml";
    }

    public String deleteForm(Category u) {
        this.entity = u;
        return "/Admin/category/deleteonay.xhtml";
    }

    public CategoryController(Categorydao dao, Category entity) {
        this.dao = dao;
        this.entity = entity;
    }

    public Categorydao getDao() {
        if (this.dao == null) {
            this.dao = new Categorydao();
        }

        return dao;
    }

    public void setDao(Categorydao dao) {
        this.dao = dao;
    }

    public Category getEntity() {
        if (this.entity == null) {
            this.entity = new Category();
        }
        return entity;
    }

    public void setEntity(Category entity) {
        this.entity = entity;
        
    }

}
