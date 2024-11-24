//Code:
package com.fh.logfile.IORegex;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


//Simulate multithread of the usage several energy objects from the battery. Control overload of the system. 

class Battery {
	private final int capacity;
	private AtomicInteger currentCharge;
	private final ReentrantLock lock = new ReentrantLock();

	private final int safeThreshold;

	public Battery(int capacity, int safeThreshold) {
		this.capacity = capacity;
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
	private Battery battery;
	private int consumeAmount;

	public EnergyConsumer(Battery battery, int consumeAmount) {
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

public class EnergyUsageSimulation {
	public static void main(String[] args) {
		Battery battery = new Battery(100, 20);

		EnergyConsumer consumer1 = new EnergyConsumer(battery, 15);
		EnergyConsumer consumer2 = new EnergyConsumer(battery, 25);
		EnergyConsumer consumer3 = new EnergyConsumer(battery, 10);

		consumer1.start();
		consumer2.start();
		consumer3.start();
	}
}



/*Output:
Consumed: 15 | Remaining charge: 85
Consumed: 10 | Remaining charge: 75
Consumed: 25 | Remaining charge: 50
Thread-2 consumed 10
Thread-0 consumed 15
Thread-1 consumed 25
Consumed: 10 | Remaining charge: 40
Thread-2 consumed 10
Consumed: 15 | Remaining charge: 25
Thread-0 consumed 15
Overload prevention! Cannot safely consume 25, current charge too low.
Thread-1 could not consume due to overload control.
Overload prevention! Cannot safely consume 10, current charge too low.
Thread-2 could not consume due to overload control.
Overload prevention! Cannot safely consume 25, current charge too low.
Thread-1 could not consume due to overload control.
Overload prevention! Cannot safely consume 15, current charge too low.
Thread-0 could not consume due to overload control.
Overload prevention! Cannot safely consume 10, current charge too low.
Thread-2 could not consume due to overload control.
Overload prevention! Cannot safely consume 15, current charge too low.
Thread-0 could not consume due to overload control.
Overload prevention! Cannot safely consume 25, current charge too low.
Thread-1 could not consume due to overload control.
Overload prevention! Cannot safely consume 10, current charge too low.
Thread-2 could not consume due to overload control.
Overload prevention! Cannot safely consume 15, current charge too low.
Thread-0 could not consume due to overload control.
Overload prevention! Cannot safely consume 25, current charge too low.
Thread-1 could not consume due to overload control.
*/
