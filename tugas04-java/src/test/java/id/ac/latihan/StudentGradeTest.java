package id.ac.latihan;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class StudentGradeTest {
    private final StudentGrade grade = new StudentGrade();

    @ParameterizedTest
    @CsvSource({"0,E", "49,E", "50,D", "59,D", "60,C",
                "69,C", "70,B", "79,B", "80,A", "100,A"})
    void gradeSesuaiBatas(int score, String expected) {
        assertEquals(expected, grade.calculateGrade(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    void gradeMenolakRentangTidakValid(int score) {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> grade.calculateGrade(score));
        assertEquals("Score must be between 0 and 100", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"0,false", "59,false", "60,true", "100,true"})
    void kelulusanSesuaiBatas(int score, boolean expected) {
        assertEquals(expected, grade.isPassed(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    void kelulusanMenolakRentangTidakValid(int score) {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> grade.isPassed(score));
        assertEquals("Score must be between 0 and 100", ex.getMessage());
    }
}
