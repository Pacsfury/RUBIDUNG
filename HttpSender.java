import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.Scanner;

public class HttpSender {

    public static String sendHttp(String value, String url) throws Exception {

        URL u = java.net.URI.create(url).toURL();
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

        byte[] out = value == null ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
        conn.setFixedLengthStreamingMode(out.length);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(out);
        }

        int status = conn.getResponseCode();
        BufferedReader reader;
        if (status >= 200 && status < 400) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream() != null ? conn.getErrorStream() : conn.getInputStream(), StandardCharsets.UTF_8));
        }
        String response = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        conn.disconnect();
        return response;
    }

    public static void playMultiplayer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("While developing the backend, this is only a simple game.");
        System.out.println("Guess the number in the host terminal. The client decides the number.\n----------------------------------------------------------");
        System.out.println("Please, enter a valid IP adress to the game host. Write 0 if you are the host.");
        String ip = sc.nextLine().trim();
        if ("0".equals(ip)) {
            try {
                String workingDirPath = System.getProperty("user.dir");
                java.io.File currentDir = new java.io.File(workingDirPath);
                java.io.File exeFile = new java.io.File(currentDir, "network.exe");

                System.out.println("Searching executable: " + exeFile.getAbsolutePath());

                if (!exeFile.exists()) {
                    System.out.println("network.exe not found at that path!");
                } else {
                    ProcessBuilder pb = new ProcessBuilder(exeFile.getAbsolutePath());
                    pb.directory(currentDir);
                    pb.inheritIO();
                    Process process = pb.start();

                    System.out.println("Initializing network.exe...");

                    Thread.sleep(500);

                    try {
                        sendHttp("SET ishosted 1", "http://127.0.0.1:8080");
                        sendHttp("SET guess 0", "http://127.0.0.1:8080");

                        String numberResp;
                        while (true) {
                            numberResp = sendHttp("GET number", "http://127.0.0.1:8080");
                            if (!numberResp.contains("Key not found")) break;
                            System.out.println("Waiting for the other player to choose a number...");
                            Thread.sleep(1000);
                        }
                        int secretNumber = Integer.parseInt(numberResp);

                        while (Integer.parseInt(sendHttp("GET guess", "http://127.0.0.1:8080")) != secretNumber) {
                            System.out.println("Try again!");
                            sendHttp(String.format("SET guess %d", Integer.parseInt(sc.nextLine().trim())), "http://127.0.0.1:8080");
                            Thread.sleep(2000);
                        }
                        System.out.println("Correct! Game over!");
                        System.exit(0);
                    } catch (Exception e) {
                        System.out.println("Error with SET ishosted: " + e.getMessage());
                    }

                    process.waitFor();
                }
            } catch (Exception e) {
                System.out.println("Error opening network.exe: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                if (sendHttp("GET ishosted", "http://" + ip + ":8080").trim().equals("1")) {
                    System.out.println("This game works a differently from classic. It's a multiplayer guess the number!");
                    System.out.println("This has been done to test my backend code. As it progresses, making RUBIDUNG online will be easier, and this will stop being this game.");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                
                    System.out.println("Choose a number:");
                    int number = Integer.parseInt(sc.nextLine().trim());

                    sendHttp(String.format("SET number %d", number), "http://" + ip + ":8080");
                } else {
                    System.out.println("Error: Having issues to connect to ip");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sc.close();
    }
}