package entity;

import java.util.Objects;

public class Comment {

    private int id;
    private String comment;
    private Score score;
    private Game game;

    public Comment() {
    }

    public Comment(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public Game getGame() {
        if (this.game == null) {
            this.game = new Game();
        }
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setId(int id) {
        this.id = id;
    }

 
    public Score getScore() {
        if (this.score == null) {
            this.score = new Score();
        }
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.comment);
        hash = 83 * hash + Objects.hashCode(this.score);
        hash = 83 * hash + Objects.hashCode(this.game);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
       
        if (!Objects.equals(this.score, other.score)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return true;
    }

}
