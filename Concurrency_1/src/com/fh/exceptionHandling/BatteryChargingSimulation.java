package com.fh.exceptionHandling;

public class BatteryChargingSimulation {

	public static void main(String[] args) {

		Battery battery1 = new Battery(100);

		// Multiple energy sources charging the battery
		EnergySource SolarPanel = new EnergySource(battery1, 5);
		EnergySource Wind = new EnergySource(battery1, 10);
		EnergySource PowerGrid = new EnergySource(battery1, 13);

		System.out.println("Current battery charge: " + battery1.getCharge());

		// Start the threads
		SolarPanel.start();
		Wind.start();
		PowerGrid.start();

		// Wait for all threads to finish
		try {
			SolarPanel.join();
			Wind.join();
			PowerGrid.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		System.out.println("Current battery charge: " + battery1.getCharge());
		
		Batteryy battery = new Batteryy(100, 20);

		EnergyConsumer consumer1 = new EnergyConsumer(battery, 15);
		EnergyConsumer consumer2 = new EnergyConsumer(battery, 25);
		EnergyConsumer consumer3 = new EnergyConsumer(battery, 10);

		consumer1.start();
		consumer2.start();
		consumer3.start();
	}
}
