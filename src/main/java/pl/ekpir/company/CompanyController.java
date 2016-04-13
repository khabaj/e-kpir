package pl.ekpir.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Krystian on 2016-03-26.
 */

@RestController
@RequestMapping(value = "${api.prefix}/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CompanyEntity getCompanyById(@PathVariable("id") Long id) {
        return companyService.getCompanyById(id);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CompanyEntity getCompanyForUser(@RequestParam("userId") Long userId) {
        if (userId != null)
            return companyService.getCompanyByUserId(userId);

        return null;
    }

    @RequestMapping(value="/{companyId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@RequestBody CompanyEntity company) {
        companyService.updateCompany(company);
    }

}
