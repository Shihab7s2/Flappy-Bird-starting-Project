import java.io.*;

public class UserManager {

    String file = "users.txt";

    void registerUser(String contact, String user, String pass) {
        try {
            FileWriter f = new FileWriter(file, true);
            f.write(contact + "," + user + "," + pass + "\n");
            f.close();
        } catch (Exception e) {
            System.out.println("Save error");
        }
    }
    boolean loginUser(String user, String pass) {
 try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            String line;

            while (true) {
                line = r.readLine();
                if (line == null) break;
                String[] arr = line.split(",");
  if (arr.length == 3) {
                if (arr[1].equals(user) && arr[2].equals(pass)) {
                       r.close();
                        return true;
                    }
                }
            }
            r.close();
        } catch (Exception e) {
            System.out.println("Read error");
        } return false;
    } boolean userExists(String user) {

        try { BufferedReader r = new BufferedReader(new FileReader(file));
            String line;

            while ((line = r.readLine()) != null) {
           String[] arr = line.split(",");

                if (arr.length == 3 && arr[1].equals(user)) {
                    r.close();
                    return true;
                }
            }
            r.close();
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}