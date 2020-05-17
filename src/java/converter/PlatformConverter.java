package converter;

import dao.Platformdao;
import entity.Platform;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "platformConverter")
public class PlatformConverter implements Converter {

    Platformdao platformdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getPlatformdao().platformBul(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object e) {
        Platform platform = (Platform) e;
        return String.valueOf(platform.getId());
    }

    public Platformdao getPlatformdao() {
        if(this.platformdao==null){
            this.platformdao=new Platformdao();
        }
        return platformdao;
    }

    public void setPlatformdao(Platformdao platformdao) {
        this.platformdao = platformdao;
    }

}