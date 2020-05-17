package entity;

import java.util.Objects;


public class Score {
    private int score_id;
    private int score;
    
    public Score() {
    
    }
    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score(int score_id, int score) {
        this.score_id = score_id;
        this.score = score;
    }
    
     @Override
    public String toString() {
        return "Score{" + "score_id=" + score_id + ", score=" + score + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.score_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        if (!Objects.equals(this.score_id, other.score_id)) {
            return false;
        }
        return true;
    }
}
