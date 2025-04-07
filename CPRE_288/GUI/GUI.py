###### START IMPORT STATEMENTS ######
import tkinter as tk
import time
import socket # For socket communication
import os
import threading # For socket communication 
import numpy as np # For plotting
import matplotlib.pyplot as plt # For plotting
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg # For displaying plot
from matplotlib.figure import Figure # For plotting
###### END IMPORT STATEMENTS ######

# Object struct
class object:
    def __init__(self, x, y, id, width):
        self.x = x
        self.y = y
        self.id = id
        self.width = width
    
    def returnId(self):
        return self.id
    
    def returnX(self):
        return self.x
    
    def returnY(self):
        return self.y
    
    def toString(self):
        return "ID = " + self.id + " X = " + self.x + " Y = " + self.y + " Width = " + self.width


###### START MAIN FUNCTION TO RUN CODE ######

def main():
    # Define objects array
    global objects
    objects = []

    global botx
    global boty

    botx=0
    boty=0

    setupTKWindow()

    # Create a Thread that will run a fuction assocated with a user defined "target" function.
    # In this case, the target function is the Client socket code
    mainThread = threading.Thread(target=socket_thread) # Creat the thread
    mainThread.start() # Start the thread

    # Start the main loop
    window.mainloop()

###### END MAIN FUNCTION TO RUN CODE ######

###### START SUPPORTING FUNCTIONS ######

# Function to enter drive mode to manually control the bot
def driveMode():
    ###### GLOBAL VARS ######
    global driveModeWindow
    global roomMapDisplay

    driveModeWindow = tk.Toplevel(window)
    driveModeWindow.title("Drive Mode")

    ## START DRIVE MODE SUPPORTING FUNCTIONS ##

    # Function to send driving events to the bot
    def sendDrive(event):
        global gui_send_message
        gui_send_message = event.char

    # Function to close drive mode and return to the main window
    def backWindow():
        driveModeWindow.withdraw()
        window.deiconify()

    ## END DRIVE MODE SUPPORTING FUNCTIONS ##

    # Take the roommap from the main window
    roomMapDisplay = tk.Canvas(driveModeWindow, width=500, height=500, bg='white')
    roomMapDisplay.grid(row=0, column=0,columnspan=2, pady=10, padx=10)

    createMapDisplay(roomMapDisplay)

    # Binds key pressed to the
    driveModeWindow.bind('<Key>', sendDrive)

    # Create the buttons
    backButton = tk.Button(driveModeWindow, width=10, text="Back", command=backWindow)
    backButton.grid(row=1, column=0, pady=10, padx=10)

    scanButton = tk.Button(driveModeWindow, width=10, text="Scan", command=sendScan)
    scanButton.grid(row=1, column=1, pady=10, padx=10)

    driveModeWindow.mainloop()

# Scan Button action.  Tells the client to send a scan request to the Cybot
def sendScan():
    global gui_send_message
    gui_send_message = "m"   # Update the message for the Client to send

# Scan Button action.  Tells the client to send a scan request to the Cybot
def sendGetObjs():
    global gui_send_message
    gui_send_message = "p"   # Update the message for the Client to send

def displayLastScan():
    # A little python magic to make it more convient for you to ajust where you want the data file to live
    # Link for more info: https://towardsthecloud.com/get-relative-path-python 
    absolute_path = os.path.dirname(__file__) # Absoult path to this python script
    relative_path = "./"   # Path to sensor data file relative to this python script (./ means data file is in the same directory as this python script
    full_path = os.path.join(absolute_path, relative_path) # Full path to sensor data file
    filename = 'lastScan.txt' # Name of sensor data file

    # angle_degrees: a vector (i.e., array of numbers) for which each element is an angle at which the sensor makes a distance measurement.
    # Units: degrees
    angle_degrees = [] # Initially empty

    # angle_radians: a vector that contains converted elements of vector angle_degrees into radians 
    # Units radians
    angle_radians = [] # Initially empty

    # distance: a vector, where each element is the corresponding distance measured at each angle in vector angle_degrees
    # Units: meters
    distance = []      # Initially empty

    # Open file containing CyBot sensor scan from 0 - 180 degrees
    file_object = open(full_path + filename,'r') # Open the file: file_object is just a variable for the file "handler" returned by open()
    file_data = file_object.readlines()  # Read the rest of the lines of the file into file_data
    file_object.close() # Important to close file one you are done with it!!

    # For each line of the file split into columns, and assign each column to a variable
    for line in file_data:
        if (line == "END\n"):
            break
        data = line.split()    # Split line into columns (by default delineates columns by whitespace)
        angle_degrees.append(float(data[1]))  # Column 0 holds the angle at which distance was measured
        distance.append(float(data[0]))       # Column 1 holds the distance that was measured at a given angle

    # Convert python sequence (list of strings) into a numpy array
    angle_degrees = np.array(angle_degrees) # Avoid "TypeError: can't multiply sequence by non-int of type float"                                            
    angle_radians = (np.pi/180) * angle_degrees # Convert degrees into radians

    # Create a polar plot
    #fig = Figure(figsize=(5,5), dpi=100)
    fig, ax = plt.subplots(subplot_kw={'projection': 'polar'}) # One subplot of type polar
    ax.plot(angle_radians, distance, color='r', linewidth=4.0)  # Plot distance verse angle (in radians), using red, line width 4.0
    ax.set_xlabel('Distance (m)', fontsize = 14.0)  # Label x axis
    ax.set_ylabel('Angle (degrees)', fontsize = 14.0) # Label y axis
    ax.xaxis.set_label_coords(0.5, 0.15) # Modify location of x axis label (Typically do not need or want this)
    ax.tick_params(axis='both', which='major', labelsize=14) # set font size of tick labels
    ax.set_rmax(2.5)                    # Saturate distance at 2.5 meters
    ax.set_rticks([0.5, 1, 1.5, 2, 2.5])   # Set plot "distance" tick marks at .5, 1, 1.5, 2, and 2.5 meters
    ax.set_rlabel_position(-22.5)     # Adjust location of the radial labels
    ax.set_thetamax(180)              # Saturate angle to 180 degrees
    ax.set_xticks(np.arange(0,np.pi+.1,np.pi/4)) # Set plot "angle" tick marks to pi/4 radians (i.e., displayed at 45 degree) increments
                                                # Note: added .1 to pi to go just beyond pi (i.e., 180 degrees) so that 180 degrees is displayed
    ax.grid(True)                     # Show grid lines

    # Create title for plot (font size = 14pt, y & pad controls title vertical location)
    ax.set_title("Last Scan", size=14, y=1.0, pad=-24)

    lastScan = tk.Canvas(window, width=500, height=500, bg='white')
    lastScan.grid(row=1, column=4, columnspan=3, rowspan=3, pady='10')

    curScanPlot = FigureCanvasTkAgg(fig, master=lastScan)
    curScanPlot.draw()
    curScanPlot.get_tk_widget().pack(fill=tk.NONE, expand=0)
    
# Client socket code (Run by a thread created in main)
def socket_thread():
    ###### GLOBAL VARS ######
    global gui_send_message
    global full_path
    global botx
    global boty
    ###### GLOBAL VARS ######


    # A little python magic to make it more convient for you to adjust where you want the data file to live
    # Link for more info: https://towardsthecloud.com/get-relative-path-python 
    absolute_path = os.path.dirname(__file__) # Absoult path to this python script
    relative_path = "./"   # Path to sensor data file relative to this python script (./ means data file is in the same directory as this python script)
    full_path = os.path.join(absolute_path, relative_path) # Full path to sensor data file  


    # TCP Socket BEGIN (See Echo Client example): https://realpython.com/python-sockets/#echo-client-and-server
    #HOST = "127.0.0.1"  # The server's hostname or IP address
    HOST = "192.168.1.1"
    #PORT = 65432        # The port used by the server
    PORT = 288
    cybot_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # Create a socket object
    cybot_socket.connect((HOST, PORT))   # Connect to the socket  (Note: Server must first be running)
                      
    cybot = cybot_socket.makefile("rbw", buffering=0)  # makefile creates a file object out of a socket:  https://pythontic.com/modules/socket/makefile

    # Sending some text
    sendCommand = "GUI on\n"
    print("Sent to server: " + sendCommand) 
    
    # Initialize GUI command message to wait
    gui_send_message = "wait\n"                               

    cybot.write(sendCommand.encode()) # Convert String to bytes (i.e., encode), and send data to the server

    # Send messges to server until user sends "quit"
    while sendCommand != 'quit\n':
                
        # Update the GUI to display command being sent to the CyBot
        lastCommand.config(text = sendCommand)
        
        # Checking for scanning event
        if (sendCommand == "m"):
            sendCommand = 'x'
            cybot.write(sendCommand.encode())
            recieveScan(cybot, "lastScan.txt")
            
        
        # Checking for recieving objects
        elif(sendCommand == "p"):
            sendCommand = 'x'
            cybot.write(sendCommand.encode())
            print("Getting objects")
            recieveObjects(cybot, "objs.txt")

        elif(sendCommand == "w" or sendCommand == "a" or sendCommand == "s" or sendCommand == "d"):
            sendCommand = 'x'
            cybot.write(sendCommand.encode())

            data = cybot.readline().decode()
            splitData = data.split()

            print(data)

            if (splitData[1].isdecimal()):
                checkNum = float(splitData[1])
            else:
                checkNum = 6969

            if (checkNum == 6969):
                botx = splitData[1]
                boty = splitData[4]

            updatedMapDisplay(roomMapDisplay)

        # Waiting for a command
        else:                
            print("Waiting for server reply\n")

        # Choose either: 1) Idle wait, or 2) Request a periodic status update from the Cybot
        # 1) Idle wait: for gui_send_message to be updated by the GUI
        while gui_send_message == "wait\n": 
            time.sleep(.1)  # Sleep for .1 second
            sendCommand = gui_send_message

        gui_send_message = "wait\n"  # Reset gui command message request to wait                        

        cybot.write(sendCommand.encode()) # Convert String to bytes (i.e., encode), and send data to the server
                
    print("Client exiting, and closing file descriptor, and/or network socket\n")
    time.sleep(2) # Sleep for 2 seconds
    cybot.close() # Close file object associated with the socket or UART
    #cybot_socket.close()  # Close the socket (NOTE: comment out if using UART interface, only use for network socket option)

# Function to recieve a scan from the bot
# Takes the cybot and filename to save the scan to
def recieveScan(cybot, filename):
    print("Requested Sensor scan from Cybot:\n")
    lastCommand.config(text = "Requesting Scan")
    rx_message = bytearray(1) # Initialize a byte array

    # Create or opens the lastScan.txt file
    file_object = open(full_path + filename,'w') # Open the file: file_object is just a variable for the file "handler" returned by open()

    # While there is still data, decode it into the file
    while (rx_message.decode() != "END\n"): # Collect sensor data until "END" recieved
        rx_message = cybot.readline()   # Wait for sensor response, readline expects message to end with "\n"
        stringMessage = rx_message.decode()
        file_object.write(stringMessage)  # Write a line of sensor data to the file
        
        # Update last recived
        updatedLastReceived(stringMessage)

    file_object.close() # Important to close file once you are done with it!!

    print("Done getting data")

    # Update and display the last scan data
    displayLastScan() 

# Function to take in objects from cybot
# Takes the cybot and file to save objects to
def recieveObjects(cybot, filename):
    print("Requested Objects from Cybot:\n")
    lastCommand.config(text = "Requesting Objects")
    rx_message = bytearray(1) # Initialize a byte array

    # Create or opens the lastScan.txt file
    file_object = open(full_path + filename,'w') # Open the file: file_object is just a variable for the file "handler" returned by open()

    # While there is still data, decode it into the file
    while (rx_message.decode() != "END\n"): # Collect sensor data until "END" recieved
        rx_message = cybot.readline()   # Wait for sensor response, readline expects message to end with "\n"
        stringMessage = rx_message.decode()
        file_object.write(stringMessage)  # Write a line of sensor data to the file
        
        # Updated last received
        #updatedLastReceived(stringMessage)

        lastReceived.insert('end', stringMessage)

    print("Got Objects")

    file_object.close() # Important to close file once you are done with it!!  
    
def updatedLastReceived(text):
    # Update the last recived box
    lastReceived.insert('end', text)

    # Check if the text box is full and delete the first line if it is
    if lastReceived.index('end') == '50.0': # Change '100.0' to your desired max length
        lastReceived.delete('1.0', '2.0')

# Function to convert objects found in text to objects
# Places them in the global "objects" array
def convertObjsToStructs(filename):
    with open(filename, 'r') as file:
        for i, line in enumerate(file):
            if i == 0: # Skip the first line
                continue
            # Formated as "id, objx, objy, botx, boty, distance, angle, width"
            parts = line.split()

            print(parts[0].isascii())
            if (parts[0].isascii()):
                break

            id = int(parts[0])
            x = int(parts[1])
            y = int(parts[2])
            width = int(parts[7])
            obj = object(x, y, id, width)
            objects.append(obj)
    
    # Updates the all objects

    # Clear the objects box
    allObjects.delete('1.0', 'end')

    # Insert the objects into the objects box
    for object in objects:
        formatted_string = f"ID: {object.id} x: {object.x} y: {object.y} width: {object.width}\n"
        #allObjects.insert('end', formatted_string)
    
    updatedObjsBox()
    updatedMapDisplay()

# Updates the object box
def updatedObjsBox():
    for obj in objects:
        print(obj.toString())
        #allObjects.insert('end', obj.toString())

# Creates the map display, only call after adding some data
def createMapDisplay(widget):
    global roomMapCanvas
    global roomMapFig

    # Width/Height of the space in cm
    widthOfSpace = 430
    heightOfSpace = 260

    # To inches
    widthOfSpace = widthOfSpace / 2.54
    heightOfSpace = heightOfSpace / 2.54

    scaleFactor = min(500 / widthOfSpace, 500 / heightOfSpace)

    # Scale the plot
    widthOfSpace = widthOfSpace * scaleFactor
    heightOfSpace = heightOfSpace * scaleFactor 

    # Create the plot
    roomMapFig = Figure(figsize=(widthOfSpace/100, heightOfSpace/100), dpi=100) # Dont ask, it just works!
    roomMapCanvas = roomMapFig.subplots(1)
    roomMapCanvas.autoscale(False)
    
    # Setup titles
    roomMapCanvas.set_title("Current Room Map", size=14, y=1.0, pad=10)
    roomMapCanvas.set_xlabel("NS", fontsize = 10.0, labelpad = 10)
    roomMapCanvas.set_ylabel("EW", fontsize = 10.0, labelpad = 10)

    # Set the limits of the x and y axes
    roomMapCanvas.set_xlim(-(widthOfSpace/2), widthOfSpace/2)
    roomMapCanvas.set_ylim(-(heightOfSpace/2), heightOfSpace/2)

    roomMapCanvas.scatter(botx, boty, s=15)

    # Creating a display for the map
    roomMapDisplay = FigureCanvasTkAgg(roomMapFig, master=widget)
    roomMapDisplay.draw()
    roomMapDisplay.get_tk_widget().pack(fill=tk.NONE, expand=0)

# Updates the map with all finished objects
def updatedMapDisplay(widget):
    for obj in objects:
        # Check to make sure objects are finished
        if obj.x != -1 and obj.y != -1:
            # Add the object to the map
            roomMapCanvas.scatter(obj.x, obj.y, s=obj.width)

    roomMapCanvas.scatter(botx, boty, s=15)

    roomMapDisplay = FigureCanvasTkAgg(roomMapFig, master=widget)
    roomMapDisplay.draw()
    roomMapDisplay.get_tk_widget().pack(fill=tk.NONE, expand=0)
            
###### END SUPPORTING FUNCTIONS ######


###### START FUNCTION TO SETUP TK WINDOW ######

def setupTKWindow():
    ###### GLOBAL VARS ######
    global window # global var for the window
    global roomMap
    global lastCommand
    global lastScan
    global lastReceived
    global allObjects
    ###### GLOBAL VARS ######

    window = tk.Tk() # Create the main window
    window.title("Room Scanning Bot GUI")

    buttonLayer = 8

    firstLabelRow = 0

    # Get the number of columns
    # UPDATE IF NEEDED
    col_count = 10

    # Configure the columns to have a minimum size
    for col in range(col_count):
        window.grid_columnconfigure(col, minsize=20)

    # Create a box for the room map
    roomMap = tk.Canvas(window, width=500, height=500, bg='white')
    roomMap.grid(row=1, column=0, columnspan=3, rowspan=3, pady='10')
    # Add text "Room Map" above the roomMap canvas
    roomMapLabel = tk.Label(window, text="Room Map", bg='white')
    roomMapLabel.grid(row=firstLabelRow, column=0, columnspan=3, sticky='ew')

    createMapDisplay(roomMap)

    # Create a box for the last scan data
    lastScan = tk.Canvas(window, width=500, height=500, bg='white')
    lastScan.grid(row=1, column=4, columnspan=3, rowspan=3, pady='10')
    # Add text "Last Scan" above the lastScan canvas
    lastScanLabel = tk.Label(window, text="Last Scan", bg='white')
    lastScanLabel.grid(row=firstLabelRow, column=4, columnspan=3, sticky='ew')

    displayLastScan()

    # Create the last received data from bot
    lastReceived = tk.Text(window, width=30, state='disabled')
    lastReceived.grid(row=1, column=9, columnspan=1, rowspan=6, sticky='nsew', pady='5')
    # Add text "Last Received" above the lastReceived text box
    lastReceivedLabel = tk.Label(window, text="Last Received", bg='white')
    lastReceivedLabel.grid(row=firstLabelRow, column=9, columnspan=1, sticky='ew')

    # Create a box for last command sent
    lastCommand = tk.Label(window, width=30, height=3)
    lastCommand.grid(row=buttonLayer, column=9, columnspan=1, rowspan=1, pady='5')
    # Add text "Last Command" above the lastCommand text box
    lastCommandLabel = tk.Label(window, text="Last Command", bg='white')
    lastCommandLabel.grid(row=6, column=9, columnspan=1, sticky='ew')

    # Create a window for objects
    allObjects = tk.Canvas(window, width=800, background='white', state='disabled')
    allObjects.grid(row=5, column=0, columnspan=7, rowspan=1, sticky='ew')
    # Create a vertical scrollbar
    scrollbar = tk.Scrollbar(window, command=allObjects.yview)
    scrollbar.grid(row=1, column=11, rowspan=6, sticky='ns')

    # Configure the text widget to use the scrollbar
    allObjects.configure(yscrollcommand=scrollbar.set)

    # Add text "All Objects" above the allObjects canvas
    allObjectsLabel = tk.Label(window, text="All Objects", bg='white')
    allObjectsLabel.grid(row=4, column=0, columnspan=7, sticky='ew')

    # Create the buttons
    button1 = tk.Button(window, text="Button 1")
    button1.grid(row=buttonLayer, column=0, columnspan=2, sticky='ew', pady='5', padx='5')

    getObjects = tk.Button(window, text="Get Objects", command=sendGetObjs)
    getObjects.grid(row=buttonLayer, column=2, columnspan=2, sticky='ew', pady='5', padx='5')

    scanButton = tk.Button(window, text="Scan", command=sendScan)
    scanButton.grid(row=buttonLayer, column=4, columnspan=2, sticky='ew', pady='5', padx='5')

    driveModeButton = tk.Button(window, text="Drive mode", command=driveMode)
    driveModeButton.grid(row=buttonLayer, column=6, columnspan=2, sticky='ew', pady='5', padx='5')

###### END FUNCTION TO SETUP TK WINDOW ######

# Run main
main()