
package sockets1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Pernille
 */
public class TimeClient 
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Socket s = new Socket("localhost", 4321);
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out.println("");
        
        System.out.println(in.readLine());
    }
    
}
