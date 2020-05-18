package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockingQueueImplTest {

	private BlockingQueue blockingQueue;
	private final int queueMaxSize = 10;

	@BeforeEach
	public void setUp() {
		blockingQueue = new BlockingQueueImpl(queueMaxSize);
	}

	@Test
	void enqueueSingleThread() throws InterruptedException {
		for (int i = 0; i < queueMaxSize; i++) {
			blockingQueue.enqueue(String.valueOf(i));
		}
		for (int i = 0; i < queueMaxSize; i++) {
			String result = blockingQueue.dequeue();
			assertEquals(String.valueOf(i), result);
		}
	}


	@Test
	void enqueueSingleThreadTry() throws InterruptedException {
		for (int i = 0; i < queueMaxSize; i++) {
			assertTrue(blockingQueue.tryEnqueue(String.valueOf(i), 1_000));
		}
		assertEquals(10, blockingQueue.size());
		assertFalse(blockingQueue.tryEnqueue("bla", 1_000));
	}


	@Test
	void dequeueSingleThreadTry() throws InterruptedException {
		for (int i = 0; i < queueMaxSize; i++) {
			assertTrue(blockingQueue.tryEnqueue(String.valueOf(i), 1_000));
		}
		assertEquals(10, blockingQueue.size());
		long now = System.currentTimeMillis();
		assertFalse(blockingQueue.tryEnqueue("bla", 1_000));
		long after = System.currentTimeMillis();
		long diff = after - now;

		assertTrue(diff >= 800 && diff <= 1_200);

		assertEquals(10, blockingQueue.size());
		assertFalse(blockingQueue.isEmpty());

		for (int i = 0; i < queueMaxSize; i++) {
			Optional<String> res = blockingQueue.tryDequeue(1_000);
			assertTrue(res.isPresent());
			assertEquals(String.valueOf(i), res.get());
		}
		Optional<String> res = blockingQueue.tryDequeue(1_000);
		assertFalse(res.isPresent());
	}

	@Test
	void assertThrowsInterrupt() {
		for (int i = 0; i < queueMaxSize; i++) {
			try {
				assertTrue(blockingQueue.tryEnqueue(String.valueOf(i), 1_000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		AtomicReference<Boolean> interrupted = new AtomicReference<>(false);
		Thread thread = new Thread(() -> {
			try {
				blockingQueue.enqueue("asd");
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupted.set(true);
			}
		});
		thread.start();

		sleep(1_000);

		thread.interrupt();
		sleep(1_000);
		assertTrue(interrupted.get());
	}


	@Test
	void enqueueSingleThreadWhenSizeIncrease() {
		Thread thread = new Thread(() -> {
			for (int i = 0; i < queueMaxSize + 1; i++) {
				try {
					blockingQueue.enqueue(String.valueOf(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		sleep(1_000);

		boolean isStillWorking = thread.isAlive();
		assertTrue(isStillWorking);

		thread.interrupt();

		sleep(1_000);

		boolean isStillWorkingAfterInterrupt = thread.isAlive();
		assertFalse(isStillWorkingAfterInterrupt);
	}

	@Test
	void dequeue() {
		Thread thread = new Thread(() -> {
			try {
				blockingQueue.dequeue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();


		boolean isStillWorking = thread.isAlive();
		assertTrue(isStillWorking);
		thread.interrupt();

		sleep(1_000);

		boolean isStillWorkingAfterInterrupt = thread.isAlive();
		assertFalse(isStillWorkingAfterInterrupt);

	}


	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void enqueueMultipleThreads() {
		Thread thread = new Thread(() -> {
			for (int i = 0; i < queueMaxSize; i++) {
				try {
					blockingQueue.enqueue(String.valueOf(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		Thread dequeuer = new Thread(() -> {

			for (int i = 0; i < queueMaxSize; i++) {
				String result;
				try {
					result = blockingQueue.dequeue();
					assertEquals(String.valueOf(i), result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		dequeuer.start();

		sleep(1_000);


		assertFalse(thread.isAlive());
		assertFalse(dequeuer.isAlive());
		assertEquals(0, blockingQueue.size());
	}

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void enqueueMultipleThreadsWhenSizeExceeds() {
		for (int j = 0; j < 10; j++) {
			Thread thread = new Thread(() -> {
				for (int i = 0; i < queueMaxSize + 10; i++) {
					try {
						blockingQueue.enqueue(String.valueOf(i));
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		for (int j = 0; j < 10; j++) {
			Thread dequeuer = new Thread(() -> {

				for (int i = 0; i < 10 * (queueMaxSize + 10); i++) {
					try {
						blockingQueue.dequeue();
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			dequeuer.start();
		}

		sleep(10_000);


		assertEquals(0, blockingQueue.size());
		assertTrue(blockingQueue.isEmpty());
	}


	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}