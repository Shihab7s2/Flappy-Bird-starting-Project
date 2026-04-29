import java.io.*;

public class UserManager {
    private final String FILE_NAME = "users.txt";

    public void registerUser(String contact, String username, String password) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(contact + "," + username + "," + password + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    public boolean loginUser(String username, String password) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    if (data[1].equals(username) && data[2].equals(password)) {
                        br.close();
                        return true;
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading user file.");
        }
        return false;
    }

    public boolean userExists(String username) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[1].equals(username)) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}