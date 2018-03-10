package extras.Internet;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {

    public static void main(String[] args) {
        try(Socket socket = new Socket("duct.org",2628);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out,"UTF-8")
        ) {
            socket.setSoTimeout(15000);
            writer.write("DEFINE eng-lat gold\r\n");
            writer.flush();
        for(String line = reader.readLine();!line.equals(".");line = reader.readLine()) System.out.println(line);
        writer.write("quit\r\n");
        writer.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
