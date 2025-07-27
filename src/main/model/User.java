package src.main.model;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class User {
	private String ci;
	private String user;
	private String userType;
	private String wallet;

	public User(String ci, String user, String userType, String wallet) {
		this.ci = ci;
		this.user = user;
		this.userType = userType;
		this.wallet = wallet;
	}

	public String getCi() {
		return this.ci;
	}

	public String getUser() {
		return this.user;
	}

	public String getUserType() {
		return this.userType;
	}

	public String getWallet() {
		try {
			String filename = "src/main/data/SCUDataBase.txt";
			List<String> lines = Files.readAllLines(Paths.get(filename));

			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (line.startsWith(this.getCi())) {
					String[] parts = line.split(",", -1);
					return parts[parts.length - 1];
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.wallet;
	}
}
