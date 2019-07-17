package domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Car")
@NamedQueries({
        @NamedQuery(name = "CarEntity.findCarsByUser", query = "SELECT c From Car c JOIN c.users u WHERE u.username = :username")
})
@Table(name = "Car")
public class CarEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @ManyToMany(mappedBy = "cars",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<UserEntity> users = new ArrayList<>();

    public CarEntity() {}

    public CarEntity(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public CarEntity(String brand, String model, List<UserEntity> users) {
        this.brand = brand;
        this.model = model;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public void addUser(UserEntity user) {
        this.users.add(user);
        user.getCars().add(this);
    }
}
