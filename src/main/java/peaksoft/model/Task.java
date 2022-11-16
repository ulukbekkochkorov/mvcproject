package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long id;

    @Column(length = 100000,name = "task_name")
    private String taskName;

    @Column(length = 100000,name = "task_text")
    private String taskText;

    public Task(String taskName, String taskText, String deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }

    @Column(length = 100000,name = "deadline")
    private String deadline;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private Lesson lesson;
}
