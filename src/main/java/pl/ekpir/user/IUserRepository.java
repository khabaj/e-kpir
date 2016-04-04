package pl.ekpir.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Krystian on 2016-03-26.
 */
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
