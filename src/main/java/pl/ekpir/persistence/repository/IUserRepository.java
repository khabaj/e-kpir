package pl.ekpir.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.ekpir.persistence.model.User;

/**
 * Created by Krystian on 2016-03-26.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
