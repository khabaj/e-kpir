package pl.ekpir.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Krystian on 2016-03-26.
 */
@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, Long> {

}
