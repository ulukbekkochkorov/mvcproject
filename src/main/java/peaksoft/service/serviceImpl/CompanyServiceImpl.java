package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        System.out.println("getAllCompaniesService");
        return companyRepository.getAllCompanies();
    }

    @Override
    public void addCompany(Company company) {
        System.out.println("addCompanyService");
        companyRepository.addCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        System.out.println("getCompanyByIdService");
        return companyRepository.getCompanyById(id);
    }

    @Override
    public void updateCompany(Company company) {
        System.out.println("updateCompanyService");
        companyRepository.updateCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        System.out.println("deleteCompanyService");
        companyRepository.deleteCompany(company);
    }
}
