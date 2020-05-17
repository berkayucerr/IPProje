package converter;

import dao.Categorydao;
import entity.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {

    Categorydao categorydao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getCategorydao().categoryBul(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object e) {
        Category category = (Category) e;
        return String.valueOf(category.getId());
    }

    public Categorydao getCategorydao() {
        if(this.categorydao==null){
            this.categorydao=new Categorydao();
        }
        return categorydao;
    }


}