package peaksoft.repository.repsositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private  EntityManager entityManager;

    @Override
    public List<Company> getAllCompanies() {
        System.out.println("getAllCompaniesRepository");
        return entityManager.createQuery("select c from Company c", Company.class).getResultList();
    }

    @Override
    public void addCompany(Company company) {
        System.out.println("addCompanyRepository");
        entityManager.persist(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        System.out.println("getCompanyByIdRepository");
        return entityManager.find(Company.class, id);
    }

    @Override
    public void updateCompany(Company company) {
        System.out.println("updateCompanyRepositoryRepository");
        entityManager.merge(company);
    }

    @Override
    public void deleteCompany(Company company) {
        System.out.println("deleteCompanyRepository");
        entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
    }
}
