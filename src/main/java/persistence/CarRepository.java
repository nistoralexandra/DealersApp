package persistence;

import domain.dto.CarDTO;
import domain.model.CarEntity;
import helper.DtoToEntityConverter;
import service.UserService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@ManagedBean(name = "carRepository")
@ApplicationScoped
public class CarRepository {

    @ManagedProperty(value = "#{userService}")
    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DealersPU");
        return emf.createEntityManager();
    }

    public void createCar(CarDTO carDTO) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CarEntity car = DtoToEntityConverter.convert(carDTO);
        em.merge(car);
        em.getTransaction().commit();
    }

    public Optional<List<CarEntity>> findCarsByUser(String username) {
        EntityManager em = getEntityManager();
        TypedQuery<CarEntity> query = em.createNamedQuery("CarEntity.findCarsByUser", CarEntity.class);
        query.setParameter("username", username);
        try {
            return Optional.of(query.getResultList());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
