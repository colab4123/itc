package kr.ac.mjc.todolist;  // 패키지명은 프로젝트에 맞게 수정해주세요

import kr.ac.mjc.todolist.Task;
import kr.ac.mjc.todolist.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByDateAndUsername(LocalDate date, String username) {
        return taskRepository.findByDateAndUsername(date, username);
    }
}