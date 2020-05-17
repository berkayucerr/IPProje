package controller;

import dao.Userdao;
import entity.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class UserController implements Serializable {

    private List<User> userList;
    private Userdao userdao;
    private User user;
    private String bul = "";
   
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

   public void ileri(){
        if (this.page ==this.getPageCount()) 
            this.page=1;
        else
        this.page++;
    }
    public void geri(){
        if (this.page==1) 
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
        this.pageCount = (int) Math.ceil(this.getUserdao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void updateForm(User uye) {
        this.user = uye;
    }

    public void clearForm() {
        this.user = new User();
    }

    public String islemIptal(){
        this.clearForm();
        return "/Admin/user/users.xhtml";
    }
    public void create() {
        this.getUserdao().insert(this.user);
        this.clearForm();
    }

    public String kayitol() {
        this.getUserdao().Kayitol(this.user);
        this.clearForm();
        return "/index.xhtml";
    }

    public String delete() {
        this.getUserdao().delete(this.user);
        this.clearForm();
        return "/Admin/user/users.xhtml";
    }
    public String deleteForm(User u) {
        this.user=u;
        return "/Admin/user/deleteonay.xhtml";
    }
    public void update() {
        this.getUserdao().update(this.user);
        this.clearForm();
    }

    public List<User> getUserlist() {
        this.userList = this.getUserdao().getKullanici(this.bul, page, pageSize);
        return userList;
    }

    public void setUserlist(List<User> uyelist) {
        this.userList = uyelist;
    }

    public Userdao getUserdao() {
        if (this.userdao == null) {
            this.userdao = new Userdao();

        }
        return userdao;
    }

    public void setUserdao(Userdao uyedao) {
        this.userdao = uyedao;
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();

        }
        return user;
    }
    public void setUser(User uye) {
        this.user = uye;
    }

    public String getBul() {
        return bul;
    }

    public void setBul(String bul) {
        this.bul = bul;
    }
}
