package persistence;

import domain.dto.UserDTO;
import domain.model.UserEntity;
import helper.DtoToEntityConverter;
import helper.EntityToDtoConverter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.Optional;

@ManagedBean(name = "userRepository")
@ApplicationScoped
public class UserRepository {

    private static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DealersPU");
        return emf.createEntityManager();
    }

    public void createUser(UserDTO userDTO) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        UserEntity user = DtoToEntityConverter.convert(userDTO);
        em.persist(user);
        em.getTransaction().commit();
    }

    public Optional<UserDTO> getUser(String username, String password) {
        EntityManager em = getEntityManager();
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getUser", UserEntity.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return Optional.of(EntityToDtoConverter.convert(query.getSingleResult()));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
