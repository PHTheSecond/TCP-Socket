// TCP simple-talk CLIENT in java

import java.net.*;
import java.io.*;

public class tcp_stalksc {

    static public BufferedReader bin;
    static public int destport = 5431;

    static public void main(String args[]) {
        String desthost = "localhost";
        if (args.length >= 1) desthost = args[0];
        bin = new BufferedReader(new InputStreamReader(System.in));

        InetAddress dest;
        System.err.print("Looking up address of " + desthost + "...");
        try {
            dest = InetAddress.getByName(desthost);
        }
        catch (UnknownHostException uhe) {
            System.err.println("unknown host: " + desthost);
            return;
        }
        System.err.println(" got it!");

        System.err.println("connecting to port " + desthost);
        Socket s;
        try {
            s = new Socket(dest, destport);
        }
        catch (IOException ioe) {
            System.err.println("connot connect to <" + desthost + "," + desthost + ">");
            return;
        }

        OutputStream sout;
        try {
            sout = s.getOutputStream();
        }
        catch (IOException ioe) {
            System.err.println("readLine() failed");
            return;
        }


        while (true) {
            String buf;
            try {
                buf = bin.readLine();
            }
            catch (IOException ioe) {
                System.err.println("readLine() failed");
                return;
            }
            if (buf == null) break; //user typed EOF character

            buf = buf + "\n"; //protocol requires sender includes \n
            byte[] bbuf = buf.getBytes();

            try {
                sout.write(bbuf);
            }
            catch (IOException ioe) {
                System.err.println("write() failed");
                return;
            }
        } //while
    }
}
