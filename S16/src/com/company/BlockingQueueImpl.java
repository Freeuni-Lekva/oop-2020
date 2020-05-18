package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueImpl implements BlockingQueue {

	int queueMaxSize;
	private final List<String> elements;
	private final Lock lock;
	private final Condition conditionEnqueue;
	private final Condition conditionDequeue;

	public BlockingQueueImpl(int queueMaxSize) {
		this.queueMaxSize = queueMaxSize;
		this.elements = new ArrayList<>();
		this.lock = new ReentrantLock();
		this.conditionEnqueue = this.lock.newCondition();
		this.conditionDequeue = this.lock.newCondition();
	}

	@Override
	public void enqueue(String elem) throws InterruptedException {
		lock.lock();
		while (elements.size() >= queueMaxSize) {
			conditionEnqueue.await();
		}
		elements.add(elem);
		conditionDequeue.signal();
		lock.unlock();
	}

	@Override
	public String dequeue() throws InterruptedException {
		lock.lock();
		while (elements.size() == 0) {
			conditionDequeue.await();
		}
		String result = elements.remove(0);
		conditionEnqueue.signal();
		lock.unlock();
		return result;
	}

	@Override
	public boolean tryEnqueue(String elem, int time) throws InterruptedException {
		lock.lock();
		boolean elapsed = true;
		while (elements.size() >= queueMaxSize && elapsed) {
			elapsed = conditionEnqueue.await(time, TimeUnit.MILLISECONDS);
		}
		if (!elapsed) {
			lock.unlock();
			return false;
		}
		elements.add(elem);
		conditionDequeue.signal();
		lock.unlock();
		return true;
	}

	@Override
	public Optional<String> tryDequeue(int time) throws InterruptedException {
		lock.lock();
		boolean elapsed = true;

		while (elements.size() == 0 && elapsed) {
			elapsed = conditionDequeue.await(time, TimeUnit.MILLISECONDS);
		}
		if (!elapsed) {
			lock.unlock();
			return Optional.empty();
		}
		String result = elements.remove(0);
		conditionEnqueue.signal();
		lock.unlock();
		return Optional.of(result);
	}

	@Override
	public int size() {
		lock.lock();
		int len = elements.size();
		lock.unlock();
		return len;
	}

	@Override
	public boolean isEmpty() {
		return 0 == size();
	}

}
