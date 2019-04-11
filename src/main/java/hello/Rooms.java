package hello;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Rooms{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer capacity;
    private String name;

    Rooms(){}
    Rooms(String n, int c, Integer xid){
        this.capacity = c;
        this.name = n;
        this.id = xid;
    }

    //Begin getters and setters
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer xID){
        this.id = xID;
    }
    public Integer getId(){
        return this.id;
    }

}