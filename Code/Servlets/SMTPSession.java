import java.io.*;
import java.net.*;
import java.util.*;

public class SMTPSession extends Object {
  public String host;   // Host name we connect to
  public int port;     // port number we connect to, default=25

  public String recipient;
  public String sender;
  public String[] message;

  protected Socket sessionSock;

  protected BufferedReader inStream;
  protected PrintWriter outStream;
public static void main(String []s)throws Exception
{
if(s.length !=3)
{
System.exit(1);
}
String []s1={"Hello","Hai"};
SMTPSession sm=new SMTPSession(s[0],s[1],s[2],s1);
sm.sendMessage();
sm.close();
}


  public SMTPSession() { }

  public SMTPSession(String host, String recipient,
	   String sender, String[] message)
			  throws IOException
  {
	   this.host = host;
	   this.port = 25;     // default SMTP port is 25

	   this.recipient = recipient;
	   this.message = message;
	   this.sender = sender;
  }

  public SMTPSession(String host, int port, String recipient,
	   String sender, String[] message)
  throws IOException
  {
	   this.host = host;
 	   this.port = port;
	   if (this.port <= 0) this.port = 25;

	   this.recipient = recipient;
	   this.message = message;
	   this.sender = sender;
  }

// Close down the session

  public void close()
  throws IOException
  {
	   sessionSock.close();
	   sessionSock = null;
  }

// Connect to the server

  protected void connect()
  throws IOException
  {
	   sessionSock = new Socket(host, port);
	   inStream = new BufferedReader(new InputStreamReader(
			sessionSock.getInputStream()));
	   outStream = new PrintWriter(
		   new OutputStreamWriter(
			sessionSock.getOutputStream()));

  }

// Send a command and wait for a response

  protected String doCommand(String commandString)
  throws IOException
  {
	   outStream.println(commandString);
	   outStream.flush();
	   String response = getResponse();
	   return response;
  }

// Get a response back from the server. Handles multi-line responses
// and returns them as part of the string.

  protected String getResponse()
  throws IOException
  {
	   String response = "";

	   for (;;) {
			String line = inStream.readLine();

			if (line == null) {
				 throw new IOException(
					  "Bad response from server.");
			}

// FTP response lines should at the very least have a 3-digit number

			if (line.length() < 3) {
				 throw new IOException(
					  "Bad response from server.");
			}
			response += line + "\n";

// If there isn't a '-' immediately after the number, we've gotten the
// complete response. ('-' is the continuation character for FTP responses)

			if ((line.charAt(3) != '-')) return response;
			//(line.length() == 3) ||
	   }
  }


// Sends a message using the SMTP protocol

  public void sendMessage()
  throws IOException
  {
	   connect();

// After connecting, the SMTP server will send a response string. Make
// sure it starts with a '2' (reponses in the 200's are positive
// responses.

	   String response = getResponse();
	   if (response.charAt(0) != '2') {
			throw new IOException(response);
	   }

// Introduce ourselves to the SMTP server with a polite "HELO"
	   response = doCommand("HELO junk");		// may be a bug. added by nosaku
	   if (response.charAt(0) != '2') {
			throw new IOException(response);
	   }

// Tell the server who this message is from

	   response = doCommand("MAIL FROM:" + sender);
	   if (response.charAt(0) != '2') {
			throw new IOException(response);
	   }


// Now tell the server who we want to send a message to

	   response = doCommand("RCPT TO:" + recipient);

	   if (response.charAt(0) != '2') {
			throw new IOException(response);
	   }

// Okay, now send the mail message

	   response = doCommand("DATA");

// We expect a response beginning with '3' indicating that the server
// is ready for data.

	   if (response.charAt(0) != '3') {
			throw new IOException(response);
	   }

// Send each line of the message

	   for (int i=0; i < message.length; i++) {

// Check for a blank line
			if (message[i].length() == 0) {
				 outStream.println();
				 continue;
			}

// If the line begins with a ".", put an extra "." in front of it.

			if (message[i].charAt(0) == '.') {
				 outStream.println("."+message[i]);
			} else {
				 outStream.println(message[i]);
			}
	   }

// A "." on a line by itself ends a message.

	   response = doCommand(".");

	   if (response.charAt(0) != '2') {
			throw new IOException(response);
	   }

	   close();
  }
}
