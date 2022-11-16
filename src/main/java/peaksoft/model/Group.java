package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    private Long id;

    @Column(length = 100000,name = "group_name")
    private String groupName;

    @Column(length = 100000,name = "dare_of_start")
    private String dateOfStart;

    @Column(length = 100000,name = "image")
    private String image;


    @ManyToMany(cascade = {PERSIST,MERGE,REFRESH,DETACH},fetch = FetchType.LAZY)
    private List<Course> courses;

    public void addCourses(Course course){
        if (courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }

    public Group(String groupName, String dateOfStart, String image) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.image = image;
    }

    @OneToMany(cascade = {ALL},fetch = FetchType.LAZY,mappedBy = "groups")
    private List<Student> students;

    public void addStudents(Student student){
        if (students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }
}
