package pl.ekpir.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ekpir.user.UserEntity;
import pl.ekpir.user.UserService;

import javax.transaction.Transactional;

/**
 * Created by Krystian on 2016-03-26.
 */
@Transactional
@Service
public class CompanyService {

    @Autowired
    ICompanyRepository companyRepository;

    @Autowired
    UserService user;

    public CompanyEntity getCompanyById(Long companyId) {
        return companyRepository.findOne(companyId);
    }

    public CompanyEntity getCompanyByUserId(Long userId) {
        UserEntity user = new UserEntity(userId);
        return companyRepository.findByUser(user);
    }

    public void updateCompany(CompanyEntity company) {

        companyRepository.save(company);
    }
}
