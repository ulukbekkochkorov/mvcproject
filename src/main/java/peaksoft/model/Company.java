package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    private Long id;

    @Column(length = 100000,name = "company_name")
    private String companyName;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    @Column(length = 100000,name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "company")
    private List<Course> courses;

    public void addCourses(Course course){

        if (courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }

}
