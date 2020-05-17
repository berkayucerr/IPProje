
package converter;

import dao.DosyaDAO;
import entity.Dosya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "dosyaConverter")
public class DosyaConverter implements Converter {

    private DosyaDAO dosyaDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDosyaDao().bul(Integer.valueOf(value));
    }

    @Override

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Dosya ds = (Dosya)arg2;
        return String.valueOf(ds.getDosya_id());
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

}
