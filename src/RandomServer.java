import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class RandomServer {
	public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java RandomServer <port number>");
            System.exit(1);
        }
         
        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(
				Integer.parseInt(args[0]));

		Socket clientSocket = serverSocket.accept();
		BufferedReader in = null;
        while (true) {
			try {
				
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream(), true);
				

				if (in==null || in.readLine() == null) {
					in = new BufferedReader(new InputStreamReader(
							new FileInputStream("C:\\randomWords.txt")));
					TimeUnit.SECONDS.sleep(5);
				} else {
					String inputLine = in.readLine();
					System.out.print("Line found");
					out.println(inputLine);
					System.out.println("Sent :" + inputLine);
				}
			} catch (Exception e) {
				System.out
						.println("Exception caught when trying to listen on port "
								+ portNumber + " or listening for a connection");
				e.printStackTrace();
			}
		}
    }
}
