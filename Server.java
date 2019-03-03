import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 
  
public class Server 
{ 
    public static void main(String args[]) throws IOException 
    { 
  
        // Step 1: Establish the socket connection. 
        ServerSocket ss = new ServerSocket(5544); 
        Socket s = ss.accept(); 
  
        // Step 2: Processing the request. 
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
  
        while (true) 
        { 
            // wait for input 
            String input = dis.readUTF(); 
  
            if(input.equals("gata")) 
                break; 
  
            System.out.println("Numere introduse: " + input); 
            String raspuns = ""; 
  
            // Use StringTokenizer to break the equation into operand and 
            // operation 
            StringTokenizer st = new StringTokenizer(input); 
  
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken()); 
            int c = Integer.parseInt(st.nextToken()); 
  
            raspuns = "Doriti sa aflati daca aceste numere pot reprezenta laturile unui triunghi(D/N)?";	
            System.out.println("Trimit raspunsul..."); 
  
            // send the result back to the client. 
            dos.writeUTF(raspuns); 
            
            input = dis.readUTF(); 
            if(input.equals("gata")) 
                break; 
            st = new StringTokenizer(input); 
            if(st.nextToken()== "D" || st.nextToken()== "") 
            {
            	if(a>0&&b>0&&c>0&&a+b>c&&b+c>a&&a+c>b)
            	{
            		raspuns = "Numerele introduse " + a + ", " + b + ", " + c + " reprezinta laturile unui triunghi. Doriti sa aflati ce fel de triunghi este(D/N)?";
            	}
            	else 
            	{
            		raspuns = "Numerele introduse " + a + ", " + b + ", " + c + " nu reprezinta laturile unui triunghi";
            	}
            }
            else
            {
            	raspuns = "Ati dorit sa va opriti aici!";
            }
            dos.writeUTF(raspuns); 
            
            input = dis.readUTF(); 
            if(input.equals("gata")) 
                break; 
            st = new StringTokenizer(input); 
            if(st.nextToken()== "D" || st.nextToken()== "") 
            {
            	if(a==b&&b==c)
					raspuns = "Triunghiul este echilateral.";
				else
					if((a*a==b*b+c*c||b*b==a*a+c*c||c*c==b*b+a*a)&&(a==b||b==c||a==c))
						raspuns= "Triunghiul este dreptunghic isoscel.";
					else 
						if((a*a==b*b+c*c)||(b*b==a*a+c*c)||(c*c==a*a+b*b))
							raspuns = "Triunghiul este dreptunghic.";
						else
							if(a==b||b==c||a==c)
								raspuns = "Triunghiul este isoscel.";
							else 
								raspuns ="Triunghiul este oarecare.";
            }
            else
            {
            	raspuns = "Ati dorit sa va opriti aici!";
            }
            dos.writeUTF(raspuns); 
        } 
    } 
} 