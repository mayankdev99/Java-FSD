package cam;

import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

class Camera {

	private int cameraID;

	private String camName;

	private String model;

	private int perDayPrice;

	private boolean available;

	public Camera(int cameraID, String camName, String model, int perDayPrice) {

		this.cameraID = cameraID;

		this.camName = camName;

		this.model = model;

		this.perDayPrice = perDayPrice;

		this.available = true;

	}

	public int getCameraID() {

		return cameraID;

	}

	public boolean isAvailable() {

		return available;

	}

	public void setAvailable(boolean available) {

		this.available = available;

	}

	public String getCameraName() {

		return camName;

	}

	public String getModel() {

		return model;

	}

	public int getPerDayPrice() {

		return perDayPrice;

	}

	public void display() {

		System.out.println("CAMERA BRAND: " + getCameraName());

		System.out.println("MODEL: " + getModel());

		System.out.println("PER DAY PRICE (INR): " + getPerDayPrice());

		String status = isAvailable() ? "AVAILABLE" : "RENTED";
		System.out.println("STATUS: " + status);

	}

}

class CameraApp {

	public static void main(String[] args) {

		double walletBalance = 0.0;

		int cameraID = 1;

		Scanner sc = new Scanner(System.in);

		System.out.println("WELCOME TO CAMERA RENTAL APP\n");

		System.out.println("PLEASE LOGIN TO CONTINUE -\n");

		System.out.println("ENTER YOUR NAME");

		String uname = sc.nextLine();

		System.out.println("ENTER YOUR PASSWORD");

		String psswrd = sc.nextLine();

		System.out.println("USERNAME - " + uname);

		System.out.println("PASSWORD - " + psswrd + "\n");

		boolean exit = false;

		int menu = 0;

		List<Camera> cameraList = new ArrayList<>();

		while (!exit) {

			switch (menu) {

			case 0:

				System.out.println("1. MY CAMERA");

				System.out.println("2. RENT A CAMERA");

				System.out.println("3. VIEW ALL CAMERAS");

				System.out.println("4. MY WALLET");

				System.out.println("5. EXIT");

				int option1 = sc.nextInt();

				sc.nextLine();

				switch (option1) {

				case 1:

					menu = 1;

					break;

				case 2:

					System.out.println("RENT A CAMERA OTION SELECTED");
					System.out.println("AVAILABLE CAMERAS:");

					System.out.println(
							"=======================================================================================");

					boolean availableCamerasExist = false;
					// table design reffered from internet"
					System.out.printf("%-10s %-30s %-20s %-15s %-10s%n", "ID", "CAMERA NAME", "MODEL", "PERDAY RENT",
							"STATUS");

					System.out.println(
							"========================================================================================");

					for (int i = 0; i < cameraList.size(); i++) {
						Camera camera = cameraList.get(i);

						String status = camera.isAvailable() ? "AVAILABLE" : "RENTED";
						if (camera.isAvailable()) {
							System.out.printf("%-10s %-30s %-20s %-15s %-10s%n", (i + 1), camera.getCameraName(),

									camera.getModel(), camera.getPerDayPrice() + " INR", status);
							availableCamerasExist = true;
						}
					}

					if (!availableCamerasExist) {
						System.out.println("NO CAMERAS ARE AVAILABLE3 FOR RENT.");
					} else {
						System.out.println("ENTER THE CAMERA ID:");
						int cameraId = sc.nextInt();
						sc.nextLine();

						// Check if the camera ID is valid
						if (cameraId > 0 && cameraId <= cameraList.size()) {
							Camera cameraToRent = cameraList.get(cameraId - 1);
							if (cameraToRent.isAvailable()) {
								double perDayRent = cameraToRent.getPerDayPrice();

								if (walletBalance >= perDayRent) {
									// Sufficient wallet balance, rent the camera
									walletBalance -= perDayRent;
									cameraToRent.setAvailable(false);
									System.out.println("CAMERA RENTED SUCESSFULLY");
								} else {

									System.out.println("TRANSACTION FAILED DUE TO INSUFFICIENT WALLET BALANCE");
								}
							}

						} else {
							System.out.println("IVALID CAMERA ID");
						}
					}

					break;
				case 3:

					System.out.println("View All Cameras option selected");

					System.out.println("ALL CAMERAS :");

					System.out.println(
							"===================================================================================");

					System.out.printf("%-10s %-30s %-20s %-15s %-10s%n", "ID", "CAMERA NAME", "MODEL", "PERDAY RENT",
							"STATUS");

					System.out.println(
							"===================================================================================");

					for (int i = 0; i < cameraList.size(); i++) {

						Camera camera = cameraList.get(i);

						String status = camera.isAvailable() ? "AVAILABLE" : "RENTED";

						System.out.printf("%-10s %-30s %-20s %-15s %-10s%n", (i + 1), camera.getCameraName(),

								camera.getModel(), camera.getPerDayPrice() + " INR", status);

					}

					break;

				case 4:

					System.out.println("MY WALLET OPTION SELECTED");

					System.out.println("YOUR WALLET BALANCE IS: INR " + walletBalance);

					System.out.println("DO YOU WANT TO DEPOSITE MORE AMOUNT TO YOUR WALLET?");
					System.out.println("1. YES");
					System.out.println("2. NO");

					int option = sc.nextInt();

					if (option == 1) {
						System.out.println("ENTER THE AMOUNT TO BE DEPOSITED (INR):");
						double depositAmount = sc.nextDouble();
						sc.nextLine();

						walletBalance += depositAmount;

						System.out.println("YOUR WALLET BALANCE HAS BEEN UPDATED SUCESSFULLY.");
						System.out.println("CURRENT WALLET BALANCE: INR " + walletBalance);
					} else if (option == 2) {
						System.out.println("NO BALANCE IS ADDED AND THE CURRENT BALANCE IS: INR" + walletBalance);
					} else {
						System.out.println("IVALID OPTION.");
					}

					System.out.println("ENTER THE CAMERA ID YOU WANT TO RENT:");
					int cameraId = sc.nextInt();
					sc.nextLine();

					// Find the camera in the cameraList
					Camera selectedCamera = null;
					for (Camera camera : cameraList) {
						if (camera.getCameraID() == cameraId) {
							selectedCamera = camera;
							break;
						}
					}

					if (selectedCamera != null) {
						double perDayRent = selectedCamera.getPerDayPrice();

						if (walletBalance >= perDayRent) {
							// Sufficient wallet balance, proceed with renting
							walletBalance -= perDayRent;

							selectedCamera.setAvailable(false);
							System.out.println("CAMERA RENTED SUCESSFULLY");
						} else {

							System.out.println("TRANSACTION FAILED DUE TO INSUFFICIENT BALANCE");
						}
					} else {

						System.out.println("INVALID CAMERA ID");
					}

					break;

				case 5:

					exit = true;

					break;

				default:

					System.out.println("INVALID OPTION");

					break;

				}

				break;

			case 1:

				System.out.println("1. ADD");

				System.out.println("2. REMOVE");

				System.out.println("3. VIEW ALL CAMERAS");

				System.out.println("4. BACK TO MAIN MENU");

				int option2 = sc.nextInt();

				sc.nextLine();

				switch (option2) {

				case 1:

					System.out.println("ADD option selected");

					System.out.println("ENTER THE CAMERA NAME:");

					String camName = sc.nextLine();

					System.out.println("ENTER THE MODEL:");

					String model = sc.nextLine();

					System.out.println("ENTER THE PER DAY PORICE (INR):");

					int perDayPrice = sc.nextInt();

					sc.nextLine();

					Camera cam = new Camera(cameraID++, camName, model, perDayPrice);

					cam.display();

					cameraList.add(cam);

					System.out.println("YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST");

					break;

				case 2:

					System.out.println("REMOVE option selected");

					System.out.println("ENTER THE CAMERA ID TO REMOVE:");

					int id = sc.nextInt();

					sc.nextLine();

					boolean found = false;

					for (Camera camera : cameraList) {

						if (camera.getCameraID() == id) {

							cameraList.remove(camera);

							found = true;

							break;

						}

					}

					if (found) {

						System.out.println("CAMERA DELATED SUCESSFULLY");

					} else {

						System.out.println("CAMERA NOT FOUND WITH GIVEN ID");

					}

					break;

				case 3:

					System.out.println("View All Cameras option selected");

					System.out.println("ALL CAMERAS :");

					System.out.println(
							"===================================================================================");

					System.out.printf("%-10s %-30s %-20s %-15s %-10s%n", "ID", "CAMERA NAME", "MODEL", "PERDAY RENT",
							"STATUS");

					System.out.println(
							"===================================================================================");

					for (int i = 0; i < cameraList.size(); i++) {

						Camera camera = cameraList.get(i);

						String status = camera.isAvailable() ? "AVAILABLE" : "RENTED";

						System.out.printf("%-10s %-30s %-20s %-15s %-10s%n", (i + 1), camera.getCameraName(),

								camera.getModel(), camera.getPerDayPrice() + " INR", status);

					}

					break;

				case 4:

					menu = 0;

					break;

				default:

					System.out.println("INVALID OPTION");

					break;

				}

				break;

			default:

				System.out.println("INVALID OPTION");

				break;

			}

		}
		System.out.println("THANK YOU FOR USING CAMERA RENTAL APP. HAVE A GOOD DAY");

		sc.close();

	}

}
