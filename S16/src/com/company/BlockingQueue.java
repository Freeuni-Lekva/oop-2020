package com.company;

import java.util.Optional;

public interface BlockingQueue {

	void enqueue(String elem) throws InterruptedException;

	String dequeue() throws InterruptedException;

	boolean tryEnqueue(String elem, int time) throws InterruptedException;

	Optional<String> tryDequeue(int time) throws InterruptedException;

	int size();

	boolean isEmpty();

}
