import java.io.*;

public class UserManager {
    private String fileName = "users.txt";

    private File getUserFile() {
        return new File(fileName);
    }

    public boolean registerUser(User user) {
        if (isUsernameExists(user.getUsername())) {
            return false;
        }

        try {
            File file = getUserFile();
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            writer.write(user.getFullName() + "," + user.getEmail() + "," + user.getPhoneNumber() + "," + user.getUsername() + "," + user.getPassword() + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        try {
            File file = getUserFile();
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    if (data[3].equals(username) && data[4].equals(password)) {
                        reader.close();
                        return true;
                    }
                }

                if (data.length == 4) {
                    if (data[2].equals(username) && data[3].equals(password)) {
                        reader.close();
                        return true;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    public boolean isUsernameExists(String username) {
        try {
            File file = getUserFile();
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    if (data[3].equals(username)) {
                        reader.close();
                        return true;
                    }
                }

                if (data.length == 4) {
                    if (data[2].equals(username)) {
                        reader.close();
                        return true;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            return false;
        }

        return false;
    }
}
