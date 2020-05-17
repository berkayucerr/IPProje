package entity;

public class Producer {
    private int producer_id;
    private String producer_name;

    public Producer() {
    }

    public Producer(int producer_id, String producer_name) {
        this.producer_id = producer_id;
        this.producer_name = producer_name;
    }

    public int getProducer_id() {
        return producer_id;
    }

    public String getProducer_name() {
        return producer_name;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public void setProducer_name(String producer_name) {
        this.producer_name = producer_name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.producer_id;
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
        final Producer other = (Producer) obj;
        if (this.producer_id != other.producer_id) {
            return false;
        }
        return true;
    }
    
}
