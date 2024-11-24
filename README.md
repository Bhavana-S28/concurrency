**Role distribution:**

**1.Bhavana Shivaraju**

**a.Energy Usage Simulation:**

Implemented the EnergyUsageSimulation class to simulate multiple energy consumers drawing power from a shared battery resource.
**Used:**
-ReentrantLock for synchronizing access to shared resources.
-AtomicInteger for managing thread-safe numeric operations.
Focused on preventing system overload by incorporating safe thresholds for energy consumption.
Demonstrated thread-safe operations and proper resource management.
**b.Features**:

**Multi-threaded Energy Consumption:**
Simulated threads representing energy-consuming devices.
Controlled access to the battery to avoid unsafe depletion.
**Dynamic Behavior:**
Introduced concurrency mechanisms to simulate real-time energy usage scenarios.

**2.Mohammad Ali Moradi
a.Battery Charging Simulation:**

Developed the BatteryChargingSimulation class to simulate multiple energy sources charging a shared battery.
**Implemented:**
Thread-based EnergySource class to model charging entities (e.g., Solar Panels, Wind Turbines).
Used thread synchronization to ensure consistent battery updates and prevent race conditions.
**b.Features:**

**Concurrent Charging:**
Simulated threads representing multiple energy sources charging a single battery.
**Thread Coordination:**
Used Thread.join() to ensure all threads complete their operations before displaying the final battery charge.

**3.Aftab Makbul Makandar
a.Theoretical Analysis:**

**Topics Covered:**

**Comparison of Concurrency Models:**
Discussed the advantages and disadvantages of various concurrency models (e.g., threads, actors, tasks).
**Concurrency vs. Parallelism:**
Provided a clear distinction between concurrency (task interleaving) and parallelism (task execution in parallel).
**Blocking vs. Non-blocking Concurrency:**
Explained the pros, cons, and use cases of both algorithms, with examples.
