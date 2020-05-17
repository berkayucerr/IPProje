package controller;

import dao.Producerdao;
import entity.Producer;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ProducerController implements Serializable {

    private Producerdao dao;
    private Producer entity;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String terim;
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

    public ProducerController() {
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

    public void create() {
        this.getDao().create(entity);
        this.clearForm();
    }

    public List<Producer> getRead() {
        return this.getDao().read(terim,page, pageSize);
    }

    public void updateForm(Producer p) {
        this.entity = p;
    }

    public String islemIptal() {
        this.clearForm();
        return "/Admin/producer/producers.xhtml";
    }

    public void clearForm() {
        this.entity = new Producer();
    }

    public void update() {
        this.getDao().update(entity);
        this.clearForm();

    }

    public String delete() {
        this.getDao().delete(this.entity);
        this.clearForm();
        return "/Admin/producer/producers";
    }

    public String deleteForm(Producer p) {
        this.entity = p;
        return "/Admin/producer/deleteonay";
    }

    public ProducerController(Producerdao dao, Producer entity) {
        this.dao = dao;
        this.entity = entity;
    }

    public Producerdao getDao() {
        if (this.dao == null) {
            this.dao = new Producerdao();
        }
        return dao;
    }

    public void setDao(Producerdao dao) {
        this.dao = dao;
    }

    public Producer getEntity() {
        if (this.entity == null) {
            this.entity = new Producer();
        }
        return entity;
    }

    public void setEntity(Producer entity) {
        this.entity = entity;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }

}
