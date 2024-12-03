package com.fh.exceptionHandling;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class Batteryy {
	private AtomicInteger currentCharge;
	private final ReentrantLock lock = new ReentrantLock();

	private final int safeThreshold;

	public Batteryy(int capacity, int safeThreshold) {
		this.safeThreshold = safeThreshold;
		this.currentCharge = new AtomicInteger(capacity);
	}

	public boolean consume(int amount) {
		lock.lock();
		try {
			if (currentCharge.get() - amount >= safeThreshold) {
				currentCharge.addAndGet(-amount);
				System.out.println("Consumed: " + amount + " | Remaining charge: " + currentCharge.get());
				return true;
			} else {
				System.out
						.println("Overload prevention! Cannot safely consume " + amount + ", current charge too low.");
				return false;
			}
		} finally {
			lock.unlock();
		}
	}

	public int getCurrentCharge() {
		return currentCharge.get();
	}
}

class EnergyConsumer extends Thread {
	private Batteryy battery;
	private int consumeAmount;

	public EnergyConsumer(Batteryy battery, int consumeAmount) {
		this.battery = battery;
		this.consumeAmount = consumeAmount;
	}

	@Override
	public void run() {
		while (true) {
			boolean success = battery.consume(consumeAmount);
			if (success) {
				System.out.println(Thread.currentThread().getName() + " consumed " + consumeAmount);
			} else {
				System.out.println(Thread.currentThread().getName() + " could not consume due to overload control.");
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}





/*
 * Output: Consumed: 15 | Remaining charge: 85 Consumed: 10 | Remaining charge:
 * 75 Consumed: 25 | Remaining charge: 50 Thread-2 consumed 10 Thread-0 consumed
 * 15 Thread-1 consumed 25 Consumed: 10 | Remaining charge: 40 Thread-2 consumed
 * 10 Consumed: 15 | Remaining charge: 25 Thread-0 consumed 15 Overload
 * prevention! Cannot safely consume 25, current charge too low. Thread-1 could
 * not consume due to overload control. Overload prevention! Cannot safely
 * consume 10, current charge too low. Thread-2 could not consume due to
 * overload control. Overload prevention! Cannot safely consume 25, current
 * charge too low. Thread-1 could not consume due to overload control. Overload
 * prevention! Cannot safely consume 15, current charge too low. Thread-0 could
 * not consume due to overload control. Overload prevention! Cannot safely
 * consume 10, current charge too low. Thread-2 could not consume due to
 * overload control. Overload prevention! Cannot safely consume 15, current
 * charge too low. Thread-0 could not consume due to overload control. Overload
 * prevention! Cannot safely consume 25, current charge too low. Thread-1 could
 * not consume due to overload control. Overload prevention! Cannot safely
 * consume 10, current charge too low. Thread-2 could not consume due to
 * overload control. Overload prevention! Cannot safely consume 15, current
 * charge too low. Thread-0 could not consume due to overload control. Overload
 * prevention! Cannot safely consume 25, current charge too low. Thread-1 could
 * not consume due to overload control.
 */