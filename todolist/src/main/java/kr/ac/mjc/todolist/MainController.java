package kr.ac.mjc.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);

        // 달력의 시작일 (이전 달의 마지막 주)
        LocalDate firstDate = currentMonth.atDay(1).minusDays(currentMonth.atDay(1).getDayOfWeek().getValue() - 1);

        // 달력의 마지막일 (다음 달의 첫 주)
        LocalDate lastDate = currentMonth.atEndOfMonth().plusDays(7 - currentMonth.atEndOfMonth().getDayOfWeek().getValue());

        List<LocalDate> calendarDates = new ArrayList<>();

        // 달력에 표시될 모든 날짜 생성
        for (LocalDate date = firstDate; !date.isAfter(lastDate); date = date.plusDays(1)) {
            calendarDates.add(date);
        }

        model.addAttribute("today", today);
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("calendarDates", calendarDates);

        return "calendar";
    }
}