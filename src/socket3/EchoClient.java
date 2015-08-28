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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Pernille
 */
public class EchoClient
{
    private static int outgoing;
    private static String wordOut;
    
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        EchoClient echo = new EchoClient();
        ArrayList<String> list  = new ArrayList<>();       
        list.add("UPPER#Hello World");
        list.add("LOWER#Hello World");
        list.add("REVERSE#abcd");
        list.add("TRANSLATE#hund");
        
        chooseOutgoingWord();
        
        Socket s = new Socket("localhost", 1234);
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out.println(""+list.indexOf(outgoing));
        
        System.out.println(in.readLine());
    }

    private static void chooseOutgoingWord()
    {
        Random rand = new Random(3);
        outgoing = rand.nextInt();
           
    }
}
