package lambdasinaction._01lambda.functional.supplier;

import java.util.function.Supplier;

class Vehicle {
	public void drive() {
		System.out.println("Driving vehicle ...");
	}
}

class Car extends Vehicle {
	@Override
	public void drive() {
		System.out.println("Driving car...");
	}
}

public class SupplierDemo {
	static void driveVehicle(Supplier<? extends Vehicle> supplier) {
		Vehicle vehicle = supplier.get();
		vehicle.drive();
	}

	public static void main(String[] args) {
		// Anonymous Func
		driveVehicle(new Supplier<Vehicle>() {
			@Override
			public Vehicle get() {
				return new Car();
			}
		});

		// Using Lambda expression
		driveVehicle(()->new Vehicle());
		
		// Using Method Reference
		driveVehicle(Car::new);
	}
}