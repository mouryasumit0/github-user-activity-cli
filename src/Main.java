import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter GitHub username: ");
        String username = scanner.nextLine();
        scanner.close();

        String GITHUB_API_URL = "https://api.github.com/users/" + username + "/events";
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GITHUB_API_URL))
                    .header("Accept", "application/vnd.github+json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("ERROR: " + response.statusCode());
                return;
            }

            JsonArray events = JsonParser.parseString(response.body()).getAsJsonArray();

            int count = 0;
            for (JsonElement eventElement : events) {
                if (count >= 10) break;

                JsonObject event = eventElement.getAsJsonObject();
                String type = event.get("type").getAsString();
                String repoName = event.getAsJsonObject("repo").get("name").getAsString();

                switch (type) {
                    case "PushEvent":
                        int commits = event.getAsJsonObject("payload")
                                .getAsJsonArray("commits").size();
                        System.out.println("- Pushed " + commits + " commit(s) to " + repoName);
                        break;
                    case "IssuesEvent":
                        System.out.println("- Opened a new issue in " + repoName);
                        break;
                    case "WatchEvent":
                        System.out.println("- Starred " + repoName);
                        break;
                    case "CreateEvent":
                        System.out.println("- Created something in " + repoName);
                        break;
                    default:
                        System.out.println("- Did " + type + " in " + repoName);
                        break;
                }
                count++;
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Request interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
