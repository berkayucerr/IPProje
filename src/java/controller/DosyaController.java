/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DosyaDAO;
import entity.Dosya;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;


@Named
@SessionScoped
public class DosyaController implements Serializable {

    private Dosya dosya;
    private List<Dosya> dosyaList;
    private DosyaDAO dosyaDao;
    private Part dos;
    private String terimAra;
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
        this.pageCount = (int) Math.ceil(this.getDosyaDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    
    private final String uploadTo = "C:/Users/berkay/Documents/NetBeansProjects/IPGameCommenters/web/dosyalar/";
    
    public void yukle(){
        try{
            InputStream veriGirisi  = dos.getInputStream();
            File f = new File(uploadTo+dos.getSubmittedFileName());
            Files.copy(veriGirisi, f.toPath());
            
            dosya = this.getDosya();
            dosya.setDosya_konumu(f.getParent());
            dosya.setDosya_ismi(f.getName());
            dosya.setDosya_tipi(dos.getContentType());
            
            this.getDosyaDao().ekle(dosya);
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }

    public String getUploadTo() {
        return uploadTo;
    }

    public Dosya getDosya() {
        if (this.dosya == null) {
            this.dosya = new Dosya();
        }
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public List<Dosya> getDosyaList() {
      return this.getDosyaDao().findAll(terim,page, pageSize);
     
    }

    public void setDosyaList(List<Dosya> dosyaList) {
        this.dosyaList = dosyaList;
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

    public void setDosyaDao(DosyaDAO dosyaDao) {
        this.dosyaDao = dosyaDao;
    }

    public Part getDos() {
        return dos;
    }

    public void setDos(Part dos) {
        this.dos = dos;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }

   

    public String getTerimAra() {
        return terimAra;
    }

    public void setTerimAra(String terimAra) {
        this.terimAra = terimAra;
    }
}
