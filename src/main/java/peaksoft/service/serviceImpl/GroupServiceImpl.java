package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroup() {
        System.out.println("getAllGroupService");
        return groupRepository.getAllGroup();
    }

    @Override
    public void addGroup(Long id, Group group) {
        System.out.println("addGroupService");
        groupRepository.addGroup(id,group);
    }

    @Override
    public Group getGroupById(Long id) {
        System.out.println("getGroupByIdService");
        return groupRepository.getGroupById(id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        System.out.println("updateGroupService");
        groupRepository.updateGroup(group,id);
    }

    @Override
    public void deleteGroup(Long id) {
        System.out.println("deleteGroupService");
        groupRepository.deleteGroup(id);
    }

    @Override
    public void assignGroup(Long courseId, Long group) {
        groupRepository.assignGroup(courseId,group);
    }
}
