/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Pernille
 */
public class TimeServer implements Runnable
{
    Socket s;
    BufferedReader in;
    String time;
    PrintWriter out;
    String incomming;
    
    public static void main(String[] args) throws IOException
    {
        String ip = "localhost";
        int port = 4321;
        if(args.length == 2)
        {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        while(true)
        {
            TimeServer e = new TimeServer(ss.accept());
            Thread t1 = new Thread(e);
            t1.start();
        }
    }

    public TimeServer(Socket soc)
    {
        s= soc;
    }

    @Override
    public void run()
    {
        time = new SimpleDateFormat("EEEEEEE MM dd hh:mm:ss z YYYY").format(Calendar.getInstance().getTime());
        try
        {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            while(true)
            {
                incomming = in.readLine();
                out.println(time);
            }
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
