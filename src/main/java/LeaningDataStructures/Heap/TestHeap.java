package LeaningDataStructures.Heap;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.Random;

public class TestHeap {
	public static void main(String[] args) {
		Queue<Integer> q = new ArrayDeque<>();

		Heap<Integer> heap = new Heap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		Random rnd = new Random();

		for (int i = 0; i < 15; i++) {
			heap.add(rnd.nextInt(100));
		}

		// 힙 내부 배열의 요소 상태
		System.out.print("내부 배열 상태 : ");
		for (Object val : heap.toArray()) {
			System.out.print(val + " ");
		}
		System.out.println();

		// 힙이 비어있을 때 까지 한 개씩 요소 뽑음
		System.out.print("힙 요소 뽑기 : \t");
		while (!heap.isEmpty()) {
			System.out.print(heap.remove() + " ");
		}

	}

}
