package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.enumModel.StudyFormat;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long id;

    public Student(String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }

    @Column(length = 100000,name = "first_name")
    private String firstName;

    @Column(length = 100000,name = "last_name")
    private String lastName;

    @Column(length = 100000,name = "phone_number")
    private String phoneNumber;

    @Column(length = 100000,name = "email")
    private String email;

    @Column(length = 100000,name = "study_format")
    private StudyFormat studyFormat;


    @ManyToOne(cascade = {PERSIST,MERGE,DETACH,REFRESH},fetch = FetchType.EAGER)
    private Group groups;
}
