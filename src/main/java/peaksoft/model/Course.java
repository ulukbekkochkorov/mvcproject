package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    private Long id;

    @Column(name = "course_name",length = 100000)
    private String courseName;

    @Column(length = 100000,name = "duration")
    private String duration;

    @Column(length = 100000,name = "description")
    private String description;

    public Course(String courseName, String duration, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.description = description;
    }

    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH},fetch = EAGER)
    private Company company;


    @ManyToMany(cascade = {PERSIST,MERGE,REFRESH,DETACH},fetch = LAZY,mappedBy = "courses")
    private List<Group> groups;

    public void addGroups(Group group){
        if (groups==null){
            groups=new ArrayList<>();
        }
        groups.add(group);
    }


    @OneToMany(cascade = {ALL},fetch = LAZY,mappedBy = "courses")
    private List<Instructor> instructors;

    public void addInstructors(Instructor instructor){
        if (instructors==null){
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
    }

    @OneToMany(cascade = {MERGE, REFRESH, REMOVE, DETACH}, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons;

    public void addLessons(Lesson lesson){
        if (lessons==null){
            lessons=new ArrayList<>();
        }
        lessons.add(lesson);
    }
}
