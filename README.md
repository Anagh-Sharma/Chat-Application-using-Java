# Chat-Application-using-Java

- Application : Multi-peer Text Broadcasting Application
- Languages   : Java, MySQL
- IDE used    : Eclipse IDE
- Purpose:	
	- Study application development in Java.
	- Understand software development standards.
	- Experiment with and demonstrate some key concepts studied in 
	  Computer Science.
- Note:	Configure config.properties file as needed to implement the code.
-------------------------------------------------------------------------------------

Concepts explored:
- Networking	:	Socket Programming
- Multithreading:	Used to implement threads for reading and writing on the input and output streams of the Server and Clients.
- JDBC:	
	- 2 Tier Architecture
	- Establish a connection with database
	- Run, DML and DQL queries
- Swing		:	Frontend
	
-------------------------------------------------------------------------------------
Preview:

- Sign-Up/Sign-In Screen:
![ChatApp1](https://user-images.githubusercontent.com/82096334/187345549-a42243df-fd6c-477b-84a2-24e8ece415db.png)

- From here, either Sign-In or Sign-Up
![ChatApp4](https://user-images.githubusercontent.com/82096334/187345592-e864c7c1-084b-4e28-a3c5-e85450429229.png)
![ChatApp5](https://user-images.githubusercontent.com/82096334/187345597-7076f1ce-2503-411e-99af-e8ed33882d4a.png)

- Signing in three users:
	- User 1:
![ChatApp6](https://user-images.githubusercontent.com/82096334/187345628-420e419c-7016-46c2-a779-7505d4bace1f.png)
![ChatApp6_1](https://user-images.githubusercontent.com/82096334/187345638-0778b553-75fd-4160-9540-32b6a8469338.png)

	- User 2:
![ChatApp7](https://user-images.githubusercontent.com/82096334/187345663-081cb18e-18a5-4d32-b57f-a520e29db6a2.png)
![ChatApp7_1](https://user-images.githubusercontent.com/82096334/187345679-7136908d-ae0a-4d41-bbdb-61e24b0d0396.png)

	- User 3:
![ChatApp8](https://user-images.githubusercontent.com/82096334/187345741-2f3b5c65-a061-4f25-b1ec-7fee0726eab5.png)
![ChatApp8_1](https://user-images.githubusercontent.com/82096334/187345748-e3558e69-e898-4de0-80ca-cddb0e987984.png)

	- The dashboards of all users:
![ChatApp9](https://user-images.githubusercontent.com/82096334/187345850-985a855a-0f59-4e3d-b6a4-d6e2e7268a1d.png)

- A simple conversion:
	- Typing first message:
![ChatApp10](https://user-images.githubusercontent.com/82096334/187345886-e54716c7-32ef-4b36-8dd0-7fef13a1b829.png)

	- Broadcasted to all clients on the Server:
![ChatApp11](https://user-images.githubusercontent.com/82096334/187345909-8f4fb49a-97b3-44e1-8ad6-55536e0cb7a0.png)

	- A simple conversation
![ChatApp13](https://user-images.githubusercontent.com/82096334/187345926-8d4a6c41-a465-46c6-8668-19b5d0e7c78e.png)

	- The chat window is indeed scrollable
![ChatApp12](https://user-images.githubusercontent.com/82096334/187345963-b7e1a505-f23a-460c-963a-9cf8f683787e.png)

-------------------------------------------------------------------------------------

Components of the application:

1. Views (UI)
	- Built using Swing and Eclipse's Window Builder
	- User Screen (Sign-In/Sign-Up)
		- Constructor starts the server.
		- Provides two methods:
			- Sign-Up:
				- Executes the signUp method of UserDAO using the entered credentials through a DTO object.
			- Sign-In:
				- Executes signIn method of UserDAO using the entered credentials through a DTO object.
				- Creates a dash board object that introduces the user to the application and informs the user of their serial indicating how many users have joined the server before them.
	- Dashboard:
		- The introduces the user to the application and informs the user 
		  of their serial indicating how many users have joined the server 
		  before them.
		- Upon clicking "Start Chat":
		- The actionPerformed method is executed, that triggers the 
		  createNewChatWindow method which:
			- Removes dash board screen and disposes its properties
			- Creates a new Chat Window object
	- Chat Window/ ChatScreen class
		- The Chat window's constructor initializes a new Client object 
		  that is passed the reference for the chat screen's TextArea.
		- The creation of the Client object triggers getMessage method 
		  of Client which:
			- Initializes the ClientMultithread object by passing the 
			  following references of the following:
				- The reference to the TextArea of the Chat Window 
				  that was passed to it.
				- The reference of the input Stream of the Client object's 
				  socket connection to the server.
		    - The getMessage also triggers ClientMultithread object's run()
		      method that takes the input from the input stream and then puts 
			  it on the TextArea object whose reference was passed to it.  
			- The messages sent from the chat window are:
		 		- Sent to the server that was triggered with 
				  the creation of the Login-In/Sign-In screen 
		          from where this dash board was created.
		        - From the server the messages are broadcast 
		          to all clients or users on that server.
		- When a User clicks on "Send" on this Chat screen or in other words 
		  executes actionPerformed method that triggers the sendMessage method 
		  which:
			- Stores the text typed in textField in a String object
			- Passes the String to the Client object's converse 
			  method. The converse method puts the String in the output stream 
			  of the client's socket to the server. The run() method of 
			  ClientMultithread already executed when this Client object 
			  was created 
			  
2. DTO (Data Transfer Object)
	- Class whose objects are used to store credentials for Sign-In/Sign-Up.
3. DAO (Data access object)
	- Used for database related functionalities
	- Classes whose objects provide database connectivity and transaction:
	- ChatDAO:	
		- Create a Connection based on the credentials in config.properties.
		- Return a Connection object.
	- UserDAO:	The class contains following two methods:
		- signUp(UserDTO userDTO):
			- Insert credentials from DTO object in the database.
			- Returns an int value which if greater than 0 indicates that the insertion was successful.
		- signIn(UserDTO userDTO):
			- Query the database with the entered user credentials 
			- If the result of the query is non-empty then, the user data exits and is thus valid.
4. Utilities
	- config.properties
		- Stores credentials for the application's database connections and 
		  networking.
	- PasswordEncryption:
		- A static method of this class is passed a password string and an 
		  encrypted version is returned.
5. Networking
	- Server:
		- An instance of the Server is created when UserScreen (Sign-In/Sign-Up) 
		  instance is created.
		- The Server class's constructor:
			- Initializes a ServerSocket object to the port defined in the 
			  config.properties file.
			- The blocking method accept() listens for client socket connection 
			  request inside an infinite while loop.
			- When a request is recieved:
				- A Server Multithread instance is created whose constructor 
				  is passed the reference for the socket that connects the 
				  server to the new client, and a reference of the Server 
				  object to broadcast the message to all Clients.
			    	- The run() method of Server Multithread is executed.
	- Server Multithread extends from Thread:
		- An instance of Server Multithread is created when a client connects 
		  to the Server.
		- The constructor is passed the reference for the socket that connects 
		  the server to the new client, and a reference of the Server object 
		  to broadcast the message to all Clients.
		- The run() method of Server Multithread is executed after the object is 
		  created.
		- The run() method:
			- Stores references of the passed socket's input and output streams.
			- Listens for an input in the input stream and when input is set,
			  it broadcasts that to all the Clients' input stream.
	- Client:
		- The Client object is created when the ChatScreen object is created.
		- The creation of the Client object triggers getMessage method 
		  of Client which:
			- Initializes the ClientMultithread object by passing the 
			  following references of the following:
				- The reference to the TextArea of the Chat Window 
				  that was passed to it.
				- The reference of the input Stream of the Client object's 
				  socket connection to the server.
		    - The getMessage also triggers ClientMultithread object's run()
		      method that takes the input from the input stream and then puts 
			  it on the TextArea object whose reference was passed to it.  
			- The messages sent from the chat window are:
		 		- Sent to the server that was triggered with 
				  the creation of the Login-In/Sign-In screen 
		          from where this dash board was created.
		        - From the server the messages are broadcast 
		          to all clients or users on that server.
		- The converse() method:
			- The converse method simply writes the message in byte form 
			  in the output stream to the server.
			- The server broadcasts the message to the input streams all 
			  the users on the server.
	- Client Multithread extends from Thread:
		- The run() method of Client Multithread does the following:
			- Takes the input from the input stream and then puts it on the 
			  TextArea object whose reference was passed to it.
	
-------------------------------------------------------------------------------------

Background image credit:
<a href="https://www.freepik.com/free-vector/hand-drawn-decorative-cartoon-speech-bubbles-sketch_15678115.htm">Image by Harryarts</a> on Freepik
