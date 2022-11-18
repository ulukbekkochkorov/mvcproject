package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Instructor;
import peaksoft.service.InstructorService;

@Controller
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping("/instructors/{id}")
    public String getAllInstructors(@PathVariable Long id, Model model) {
        System.out.println("getAllInstructorController");
        model.addAttribute("instructors", instructorService.getAllInstructor(id));
        model.addAttribute("courseId",id);
        return "/instructor/instructors";
    }


    @GetMapping("/getInstructor/{id}")
    public String getInstructorById(@PathVariable("id") Long id, Model model) {
        System.out.println("getInstructorByIdController");
        model.addAttribute("instructors", instructorService.getInstructorById(id));
        return "/instructor/instructors";
    }

    @GetMapping("/instructors/{id}/addInstructor")
    public String addInstructor(@PathVariable Long id, Model model) {
        System.out.println("addInstructorController");
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courseId", id);
        return "/instructor/addInstructor";
    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courseId", instructor.getCourses().getId());
        return "/instructor/updateInstructor";
    }

    @PostMapping("/{id}/saveInstructors")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor, @PathVariable Long id) {
        instructorService.addInstructor(id, instructor);
        return "redirect:/instructors/" + id;
    }
    @PostMapping("/{courseId}/{id}/saveUpdateInstructor")
    public String saveUpdateInstructor(@PathVariable("courseId") Long courseId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(instructor, id);
        return "redirect:/instructors/" + courseId;
    }


    @GetMapping("/{courseId}/{id}/deleteInstructor")
    public String deleteInstructor(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        System.out.println("deleteInstructorController");
        instructorService.deleteInstructor(id);
        return "redirect:/instructors/"+courseId;
    }
}
