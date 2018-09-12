
public class CarDemo {
	public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("Honda");
        System.out.println("Brand = " + car.getBrand());
        car.setNumberOfSeat(5);
        car.getNumberOfSeat();
        //
        // The setBrand() and getBrand() is inherited from the Car
        // class.
        //
        Truck truck = new Truck();
        truck.setBrand("Ford");
        System.out.println("Brand = " + truck.getBrand());
        truck.setLoadCapacity(100);
        System.out.println("Truck capacity= "+truck.getLoadCapacity());
        
        //
        // The setBrand(), getBrand() and setNumberOfSeat methods
        // are is inherited from the Car class.
        //
        Sedan sedan = new Sedan();
        sedan.setBrand("Hyundai");
        System.out.println("Brand = " + sedan.getBrand());
        sedan.setNumberOfSeat(2);
        System.out.println("Number of seat = "+sedan.getNumberOfSeat());
        sedan.setGearType(5);
        System.out.println("GearType = "+sedan.getGearType());
        sedan.setWheel(10);
        System.out.println("Wheel = "+sedan.getWheel());
    }

}
