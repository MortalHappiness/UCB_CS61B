import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class TestArrayDequeGold {
  private static final int N = 10;

  @Test
  public void testDequeCorrectness() {
    ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
    StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
    List<String> operations = new ArrayList<>();

    while (true) {
      int op = StdRandom.uniform(4);
      switch (op) {
        case 0:
          addFirst(solution, student, operations);
          break;
        case 1:
          addLast(solution, student, operations);
          break;
        case 2:
          if (solution.size() > 0) {
            removeFirst(solution, student, operations);
          } else {
            addFirst(solution, student, operations);
          }
          break;
        case 3:
          if (solution.size() > 0) {
            removeLast(solution, student, operations);
          } else {
            addLast(solution, student, operations);
          }
          break;
        default:
          break;
      }
    }
  }

  private static void addFirst(
      ArrayDequeSolution<Integer> solution,
      StudentArrayDeque<Integer> student,
      List<String> operations) {
    int num = StdRandom.uniform(N);
    solution.addFirst(num);
    student.addFirst(num);
    operations.add("addFirst(" + num + ")");
  }

  private static void addLast(
      ArrayDequeSolution<Integer> solution,
      StudentArrayDeque<Integer> student,
      List<String> operations) {
    int num = StdRandom.uniform(N);
    solution.addLast(num);
    student.addLast(num);
    operations.add("addLast(" + num + ")");
  }

  private static void removeFirst(
      ArrayDequeSolution<Integer> solution,
      StudentArrayDeque<Integer> student,
      List<String> operations) {
    operations.add("removeFirst()");
    assertEquals(String.join("\n", operations), solution.removeFirst(), student.removeFirst());
  }

  private static void removeLast(
      ArrayDequeSolution<Integer> solution,
      StudentArrayDeque<Integer> student,
      List<String> operations) {
    operations.add("removeLast()");
    assertEquals(String.join("\n", operations), solution.removeLast(), student.removeLast());
  }
}
