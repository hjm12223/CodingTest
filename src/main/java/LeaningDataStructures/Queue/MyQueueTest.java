package LeaningDataStructures.Queue;

public class MyQueueTest {
	public static void main(String[] args) {
		MyArrayQueue<Student> q = new MyArrayQueue<>();

		q.offer(new Student("김자바", 92));
		q.offer(new Student("이시플", 72));
		q.offer(new Student("조시샵", 98));
		q.offer(new Student("파이손", 51));

		for (Object a : q.toArray()) {
			System.out.println(a.toString());
		}
	}

	public static class Student {
		String name;
		int score;

		Student(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String toString() {
			return "이름 : " + name + "\t성적 : " + score;
		}
	}
}
