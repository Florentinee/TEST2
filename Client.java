import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 
  
public class Client 
{ 
    public static void main(String[] args) throws IOException 
    { 
        InetAddress ip = InetAddress.getLocalHost(); 
        int port = 5544; 
        Scanner sc = new Scanner(System.in); 
  
        // Step 1: Open the socket connection. 
        Socket s = new Socket(ip, port); 
  
        // Step 2: Communication-get the input and output stream 
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
  
        while (true) 
        { 
            // Enter the equation in the form- 
            // "operand1 operation operand2" 
            System.out.print("Introduceti trei numere naturale sub forma: "); 
            System.out.println("'5 7 9'"); 
  
            String inp = sc.nextLine(); 
  
            if (inp.equals("gata")) 
                break; 
  
            // send the equation to server 
            dos.writeUTF(inp); 
  
            // wait till request is processed and sent back to client 
            String ans = dis.readUTF(); 
            System.out.println("Raspuns = " + ans); 
            
            inp = sc.nextLine(); 
            
            if (inp.equals("gata")) 
                break; 
            dos.writeUTF(inp);
            
            ans = dis.readUTF(); 
            System.out.println("Raspuns = " + ans); 
            
            inp = sc.nextLine(); 
            
            if (inp.equals("gata")) 
                break; 
            dos.writeUTF(inp);
            
            ans = dis.readUTF(); 
            System.out.println("Raspuns = " + ans); 
        } 
    } 
} 