package controller;

import dao.YetkiDAO;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private YetkiDAO yetkidao;
    private User user;

    public String login() {
        user = this.getYetkidao().girisYap(this.user.getMail(), this.user.getSifre());
        if (this.user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hoşgeldiniz" + " " + user.getAd() + " " + user.getSoyad()));
            if (user.getAdmin() == 1) {
                return "/Admin/user/users.xhtml";
            }
            return "/front/front.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı kullanıcı adı veya şifre"));
            return "";
        }
    }

    public YetkiDAO getYetkidao() {
        if (this.yetkidao == null) {
            this.yetkidao = new YetkiDAO();
        }
        return yetkidao;
    }

    public User getUser() {
        return user == null ? user = new User() : user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
