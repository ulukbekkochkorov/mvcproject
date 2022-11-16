package peaksoft.repository.repsositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = entityManager.createQuery("select t from Task t", Task.class).getResultList();
        return tasks;
    }

    @Override
    public void addTask(Long id, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        lesson.addTasks(task);
        task.setLesson(lesson);
        entityManager.merge(lesson);
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void updateTask(Task task, Long id) {
        Task task1 = entityManager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task1);
    }

    @Override
    public void deleteTask(Long id) {
        entityManager.remove(entityManager.find(Task.class, id));
    }
}
