package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.model.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

@Controller
public class StudentController {

  private final StudentService studentService;

  private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/students/{groupId}")
    public String getAllStudents(@PathVariable Long groupId, Model model,
                                 @ModelAttribute("group") Group group){
        model.addAttribute("students", studentService.getAllStudents(groupId));
        model.addAttribute("groupId", groupService.getAllGroup());
        return "/student/students";
    }

//    @PostMapping("/{courseId}/{companyId}/assignStu")
//    public String assignStudent(@PathVariable("courseId") Long groupId,
//                                @ModelAttribute("student") Student student){
//        studentService.assignStudent(groupId, student.getId());
//        return "redirect:/courses/" + groupId;
//    }

    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "/student/students";
    }

    @GetMapping("/{groupId}/addStudent")
    public String addStudent(@PathVariable("groupId") Long groupId, Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("groupId", groupId);
        return "/student/addStudent";
    }

    @PostMapping("/{groupId}/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student,
                              @PathVariable("groupId") Long groupId){
        studentService.addStudent(groupId, student);
        return "redirect:/students/" + groupId;
    }


    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("groupId", student.getGroups().getId());
        return "/student/updateStudent";
    }

    @GetMapping("/{groupId}/{id}/updateStudent")
    public String saveUpdateStudent(@PathVariable("groupId") Long groupId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("student") Student student) {
        studentService.updateStudent(student, id);
        return "redirect:/students/" + groupId;
    }

//    @GetMapping("/{groupId}/{id}/deleteStudent")
//    public String deleteStudent(@PathVariable("id") Long id,
//                                @PathVariable("groupId") Long groupId){
//        studentService.deleteStudent(id);
//        return "redirect:/students/" + groupId;
//    }
    @GetMapping("/{groupId}/{id}/deleteStudent")
    public String deleteLesson(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId) {
        studentService.deleteStudent(id);
        return "redirect:/students/" + groupId;
    }
}
