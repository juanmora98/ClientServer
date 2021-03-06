package com.escuelaing.arep.clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * EchoClient
 */
public class EchoClient {

    public static void main(String[] args) throws IOException {
        System.out.println("Client UP");
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream(), StandardCharsets.UTF_8));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
            e.printStackTrace();
            System.exit(1);                        
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String input, output;
        while ((input = reader.readLine()) != null) {
            out.println(input);
            output = in.readLine();
            System.out.println(output);
            if (output.equals("Respuesta: Bye.")) 
                break;
        }

        out.close();
        in.close();
        reader.close();
        echoSocket.close();
    }
}