package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    private Long id;

    public Instructor(String firstName, String lastName, String phoneNumber, String email, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }

    @Column(length = 100000,name = "first_name")
    private String firstName;

    @Column(length = 100000,name = "last_name")
    private String  lastName;

    @Column(length = 100000,name = "phone_number")
    private String phoneNumber;

    @Column(length = 100000,name = "email")
    private String email;

    @Column(length = 100000,name = "specialization")
    private String specialization;


    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH},fetch = FetchType.EAGER)
    private Course courses;
}
