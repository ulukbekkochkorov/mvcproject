package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.model.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

@Controller
public class GroupController {
    private final GroupService groupService;
    private final CompanyService companyService;
    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService, CompanyService companyService, StudentService studentService) {
        this.groupService = groupService;
        this.companyService = companyService;
        this.studentService = studentService;
    }




    @GetMapping("/groups/{id}")
    public String getAllGroup(@PathVariable Long id, Model model,
                                @ModelAttribute("student") Student student) {
        model.addAttribute("groups", groupService.getAllGroup());
        model.addAttribute("companyId",id);
        model.addAttribute("students", studentService.getAllStudents(id));
        return "/group/groups";
    }


    @PostMapping("/{courseId}/{groupId}/assignInsToCourse")
    public String assignCourse(@PathVariable("groupId") Long courseId,
                                   @PathVariable("courseId") Long companyId,
                                   @ModelAttribute("student") Student student){
        studentService.assignStudent(courseId, student.getId());
        return "redirect:/groups/" + companyId;
    }



    @GetMapping("/getGroups/{id}")
    public String getGroupById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("groups", groupService.getGroupById(id));
        return "/course/courses";
    }

    @GetMapping("/courses/{id}/addCourse")
    public String addGroup(@PathVariable Long id, Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("courseId", id);
        return "/group/addGroup";
    }

    @PostMapping("/{id}/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group,
                             @PathVariable Long id) {
        groupService.addGroup(id, group);
        return "redirect:/groups/"+id;
    }

//    @GetMapping("/update/{id}")
//    public String updateGroup(@PathVariable("id") Long id, Model model) {
//        Group group = groupService.getGroupById(id);
//        model.addAttribute("group", group);
//        model.addAttribute("companyId", group.getCourses().getId());
//        return "/group/updateGroup";
//    }

    @PostMapping("/{courseId}/{id}/updateGroup")
    public String saveUpdateGroup(@PathVariable("courseId") Long companyId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("group") Group group) {
        groupService.updateGroup(group,id);
        return "redirect:/group/"+companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteGroup")
    public String deleteGroup(@PathVariable("id") Long id, @PathVariable("courseId") Long companyId) {
        groupService.deleteGroup(id);
        return "redirect:/group/"+companyId;
    }
}
