package src.main.model;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SesionUser {
	private static SesionUser instance;
	private User sesionUser;

	private SesionUser() {} 

	public static SesionUser getInstance() {
		if (instance == null) {
			instance = new SesionUser();
		}
		return instance;
	}

	public void setSesionUser(User user) {
		this.sesionUser = user;
	}

	public User getUser() {
		return sesionUser;
	}

	public void logout() {
		this.sesionUser = null;
	}

	public int setWallet(String amount) {
		try {
			String filename = "src/main/data/SCUDataBase.txt";
			List<String> lines = Files.readAllLines(Paths.get(filename));

			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (line.startsWith(instance.getUser().getCi())) {
					String[] parts = line.split(",", -1);
					parts[parts.length - 1] = Float.toString(
						Float.parseFloat(parts[parts.length - 1])
						+ Float.parseFloat(amount)
					);
					lines.set(i, String.join(",", parts));
					Files.write(Paths.get(filename), lines);
					return 0;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
}
