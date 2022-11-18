package peaksoft.repository.repsositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Group> getAllGroup() {
        System.out.println("getAllGroupRepository");
         return entityManager.createQuery("select c from Group c", Group.class).getResultList();
    }

    @Override
    public void addGroup(Long id, Group group) {
        System.out.println("addGroupRepository");
        Course course=entityManager.find(Course.class,id);
        course.addGroups(group);
        group.addCourses(course);
        entityManager.merge(group);
    }


    @Override
    public Group getGroupById(Long id) {
        System.out.println("getGroupByIdRepository");
        return entityManager.find(Group.class, id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        System.out.println("updateGroupRepository");
        Group group1 = entityManager.find(Group.class,id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        System.out.println("deleteGroupRepository");
        entityManager.remove(entityManager.find(Group.class, id));
    }

    @Override
    public void assignGroup(Long courseId, Long groupId) {
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        group.addCourses(course);
        course.addGroups(group);
        entityManager.merge(group);
        entityManager.merge(course);
    }
}
