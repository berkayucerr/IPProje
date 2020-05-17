package Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("validator")
@SessionScoped
public class Validator implements Serializable {

    private Collection<FacesMessage> mesaj = new ArrayList<>();

    public boolean validateAd(FacesContext fc, UIComponent uı, Object v) {

        boolean isValid = true;
        mesaj.clear();

        String value = (String) v;
        if (value.equals("")) {
            mesaj.add(new FacesMessage("Lütfen Adınızı Giriniz :) "));
            isValid = false;
        } else if (value.length() < 3 || value.length() > 50) {
            mesaj.add(new FacesMessage("Ad 3'den Küçük 20 den Büyük Olmamalı!"));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(mesaj);
        } else {
            return true;
        }

    }

    public boolean validateSoyad(FacesContext fc, UIComponent uı, Object v) {

        boolean isValid = true;
        mesaj.clear();

        String value = (String) v;
        if (value.equals("")) {
            mesaj.add(new FacesMessage("Lütfen Soyadınızı Giriniz  :) "));
            isValid = false;
        } else if (value.length() < 2 || value.length() > 30) {
            mesaj.add(new FacesMessage("Soyad 2'den Küçük 15 den Büyük Olmamalı!"));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(mesaj);
        } else {
            return true;
        }

    }


    public boolean validateEmail(FacesContext fc, UIComponent uı, Object v) {
        boolean isValid = true;
        mesaj.clear();

        String value = (String) v;
        value = value.trim();

        if (value == null || value.equals("")) {
            mesaj.add(new FacesMessage("Lütfen Email Adresinizi Giriniz :) "));
            isValid = false;
        } else if (!value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            mesaj.add(new FacesMessage("Email Adresiniz Şu Şekilde Olmalı ÖRN:(example@example.com) "));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(mesaj);

        } else {
            return true;
        }
    }

    public boolean validateSifre(FacesContext fc, UIComponent uı, Object v) {

        boolean isValid = true;
        mesaj.clear();

        String value = (String) v;
        if (value.equals("")) {
            mesaj.add(new FacesMessage("Lütfen Şifrenizi Giriniz  :) "));
            isValid = false;
        } else if (value.length() < 5) {
            mesaj.add(new FacesMessage("Şifre En Az 5 Haneli Olmalı!"));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(mesaj);
        } else {
            return true;
        }

    }

    public Collection<FacesMessage> getMsgList() {
        return mesaj;
    }

    public void setMsgList(Collection<FacesMessage> msgList) {
        this.mesaj = msgList;
    }

}
