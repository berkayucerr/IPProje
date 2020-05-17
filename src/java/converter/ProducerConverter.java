package converter;

import dao.Producerdao;
import entity.Producer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "producerConverter")
public class ProducerConverter implements Converter {

    Producerdao producerdao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getProducerdao().producerBul(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object e) {
        Producer producer = (Producer) e;
        return String.valueOf(producer.getProducer_id());
    }

    public Producerdao getProducerdao() {
        if(this.producerdao==null){
            this.producerdao=new Producerdao();
        }
        return producerdao;
    }

    public void setProducerdao(Producerdao producerdao) {
        this.producerdao = producerdao;
    }

}