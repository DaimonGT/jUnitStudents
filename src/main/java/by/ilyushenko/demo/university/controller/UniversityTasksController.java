package by.ilyushenko.demo.university.controller;

import by.ilyushenko.demo.university.data.UniversityFactory;
import by.ilyushenko.demo.university.model.Lesson;
import by.ilyushenko.demo.university.model.Student;
import by.ilyushenko.demo.university.model.University;
import by.ilyushenko.demo.university.service.UniversityTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/university")
public class UniversityTasksController {

	private final UniversityTasksService service;

	@Autowired
	public UniversityTasksController(UniversityTasksService service) {
		this.service = service;
	}

	@GetMapping("/students/count")
	public ResponseEntity<Integer> countStudents() {
		University uni = UniversityFactory.createSampleUniversity();
		return ResponseEntity.ok(service.countTotalStudents(uni));
	}

	@GetMapping("/students/by-name")
	public ResponseEntity<List<Student>> findStudentsByName(@RequestParam String name) {
		University uni = UniversityFactory.createSampleUniversity();
		return ResponseEntity.ok(service.findStudentsByName(uni, name));
	}

	@GetMapping("/students/by-year/{year}")
	public ResponseEntity<List<Student>> findStudentsByYear(@PathVariable int year) {
		University uni = UniversityFactory.createSampleUniversity();
		return ResponseEntity.ok(service.findStudentsByYear(uni, year));
	}

	@GetMapping("/lessons/by-subject")
	public ResponseEntity<List<Lesson>> findLessonsBySubject(@RequestParam String subject) {
		University uni = UniversityFactory.createSampleUniversity();
		return ResponseEntity.ok(service.findLessonsBySubject(uni, subject));
	}

	@GetMapping("/grades/average-by-subject")
	public ResponseEntity<Map<String, Double>> getAverageGradesBySubject() {
		University uni = UniversityFactory.createSampleUniversity();
		return ResponseEntity.ok(service.calculateAverageGradeBySubject(uni));
	}

	@GetMapping("/ranking")
	public ResponseEntity<List<Map.Entry<Student, Double>>> getStudentRanking() {
		University uni = UniversityFactory.createSampleUniversity();
		return ResponseEntity.ok(service.getStudentRanking(uni));
	}
}

