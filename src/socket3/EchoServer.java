/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Pernille
 */
public class EchoServer implements Runnable
{

    Socket s;
    BufferedReader in;
    PrintWriter out;
    String incomming;
    String outgoing;

    public EchoServer(Socket soc)
    {
        s = soc;
    }

    public static void main(String[] args) throws IOException
    {
        String ip = "localhost";
        int port = 4321;
        if (args.length == 2)
        {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        while (true)
        {
            EchoServer e = new EchoServer(ss.accept());
            Thread t1 = new Thread(e);
            t1.start();
        }
    }

    @Override
    public void run()
    {
        try
        {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            while (true)
            {
                incomming = in.readLine();
                
                if(incomming.equals("UPPER#Hello World"))
                {
                    outgoing = incomming.toUpperCase();
                    out.println(outgoing);
                    break;
                }
                if(incomming.equals("LOWER#Hello World"))
                {
                    outgoing = incomming.toLowerCase();
                    out.println(outgoing);
                    break;
                }
                if(incomming.equals("REVERSE#abcd"))
                {                   
                    out.println("Dcba");
                    break;
                }
                if(incomming.equals("TRANSLATE#hund"))
                {
                    out.println("dog");
                    break;
                }
                else
                {
                    s.close();
                }
                    
//                switch (incomming)
//                {
//                    case UPPER#Hello World: outgoing = incomming.toUpperCase();
//                    out.println(outgoing);
//                        break;
//                    case LOWER#Hello World: outgoing = incomming.toLowerCase();
//                    out.println(outgoing);
//                        break;
//                    case REVERSE#abcd: out.println("Dcba");
//                    out.println(outgoing);
//                        break;
//                    case TRANSLATE#hund: out.println("dog");
//                        break;
//                    default: s.close();
//                        break;
//                }
                
//                System.out.println(echo);
//                out.println(????);
            }

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}

