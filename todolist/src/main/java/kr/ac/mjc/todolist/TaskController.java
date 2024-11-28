package kr.ac.mjc.todolist;  // 패키지명은 프로젝트에 맞게 수정해주세요

import kr.ac.mjc.todolist.Task;
import kr.ac.mjc.todolist.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/add/{date}")
    public String showAddTaskForm(@PathVariable String date, Model model) {
        Task task = new Task();
        task.setDate(LocalDate.parse(date));
        model.addAttribute("task", task);
        return "add-task";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "add-task";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task, @AuthenticationPrincipal UserDetails userDetails) {
        task.setUsername(userDetails.getUsername());
        taskService.saveTask(task);
        return "redirect:/calendar";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/calendar";
    }

    @GetMapping("/list/{date}")
    @ResponseBody
    public List<Task> getTasksByDate(@PathVariable String date, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getTasksByDateAndUsername(
                LocalDate.parse(date),
                userDetails.getUsername()
        );
    }
}