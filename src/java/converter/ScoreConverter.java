package converter;

import dao.Scoredao;
import entity.Score;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "scoreConverter")
public class ScoreConverter implements Converter {

    Scoredao scoredao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getScoredao().scoreBul(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object e) {
        Score score = (Score) e;
        return String.valueOf(score.getScore_id());
    }

    public Scoredao getScoredao() {
        if(this.scoredao==null){
            this.scoredao=new Scoredao();
        }
        return scoredao;
    }

   

}