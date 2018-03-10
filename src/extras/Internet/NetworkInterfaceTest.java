package extras.Internet;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceTest {
    public static void main(String[] args) {
        try {
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                System.out.println(interfaces.nextElement());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
