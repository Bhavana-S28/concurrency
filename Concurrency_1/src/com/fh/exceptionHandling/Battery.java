package com.fh.exceptionHandling;

class Battery {
	private int charge = 0;
	protected final int capacity;

	public Battery(int capacity) {
		this.capacity = capacity;
	}

	public synchronized void charge(int amount) {
		if (charge + amount > capacity) {
			charge = capacity;
			System.out.println("Battery fully charged to " + charge + "/" + capacity);
		} else {
			charge += amount;
			System.out.println("Battery charged to " + charge + "/" + capacity);

		}

	}

	public int getCharge() {
		return charge;
	}
}