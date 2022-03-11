package ro.ase.cts.entity;

public class Employee {
	private int id;
	private String name;
	private String address;
	private double salary;

	public Employee(int id, String name, String address, double salary) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id: ");
		builder.append(id);
		builder.append("\nname: ");
		builder.append(name);
		builder.append("\naddress: ");
		builder.append(address);
		builder.append("\nsalary: ");
		builder.append(salary);
		return builder.toString();
	}
}
