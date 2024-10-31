package concurrency;

class EnergySource extends Thread {
	private final Battery battery;
	private final int chargeAmount;

	public EnergySource(Battery battery, int chargeAmount) {
		this.battery = battery;
		this.chargeAmount = chargeAmount;
	}

	@Override
	public void run() {

		while (battery.getCharge() < battery.capacity) {
			battery.charge(chargeAmount);

			try {
				Thread.sleep(100); // Simulate charging time
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("Battery charged with: " + this.getName());

	}
}
