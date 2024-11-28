package kr.ac.mjc.todolist;  // 패키지명은 프로젝트에 맞게 수정해주세요

import kr.ac.mjc.todolist.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDateAndUsername(LocalDate date, String username);
}