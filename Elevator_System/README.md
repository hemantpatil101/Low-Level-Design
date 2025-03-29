## **Elevator Request Handling Algorithm**

### **1. Assigning Requests to the Correct Queue**
- A request is added to the **UpQueue** if the destination floor is **above** the elevator’s current floor.  
- Otherwise, it is added to the **DownQueue**.  
- If the elevator is **IDLE**, it moves in the direction of the non-empty queue.

### **2. Selecting the Best Elevator for a Request**
1. **First Priority:** Assign the request to an **IDLE elevator** (as it can move freely in any direction).  
2. **Second Priority:** If no IDLE elevator is available, choose an elevator **already moving in the request's direction** and able to pick up the request **without reversing**.  
3. **Final Priority:** If no suitable moving elevator is found, assign the **nearest available elevator**.