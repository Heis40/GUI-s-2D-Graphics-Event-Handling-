# GUI-s-2D-Graphics-Event-Handling-

AI reflection:
The AI that I had used was Copilot. I had used the AI to help troubleshoot some errors and try to understand certain concepts. For example, I had an issue when the program was run, the GUI would show a blank screen and the terminal and gave a long error. I tried to read the error, but I could not understand what it meant being that it had lots of abbreviations. So, I had copy and pasted the error message into copilot, which told me what the error was and suggested ways to fix it. The error was that the GUI was apparently too big to show on the screen. So, I had to go back to the creation of the canvas and make sure that the dimensions were correct. However, none of my attempts to fix it seemed to work, so I let AI attempt to fix it, which it did. My new goal is to try and learn how to read these messages so that I do not have to rely on AI constantly for understanding what those error messages mean.

Example prompt: 
java.lang.NullPointerException: Cannot invoke "com.sun.prism.RTTexture.createGraphics()" because "<local9>" is null
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGCanvas$RenderBuf.validate(NGCanvas.java:214)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGCanvas.initCanvas(NGCanvas.java:644)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGCanvas.renderContent(NGCanvas.java:607)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGNode.doRender(NGNode.java:2074)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGNode.render(NGNode.java:1966)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGGroup.renderContent(NGGroup.java:270)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGRegion.renderContent(NGRegion.java:579)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGNode.doRender(NGNode.java:2074)
at javafx.graphics@21.0.8/com.sun.javafx.sg.prism.NGNode.render(NGNode.java:1966)
at javafx.graphics@21.0.8/com.sun.javafx.tk.quantum.ViewPainter.doPaint(ViewPainter.java:480)
at javafx.graphics@21.0.8/com.sun.javafx.tk.quantum.ViewPainter.paintImpl(ViewPainter.java:329)
at javafx.graphics@21.0.8/com.sun.javafx.tk.quantum.PresentingPainter.run(PresentingPainter.java:92)
at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:572)
at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java:358)
at javafx.graphics@21.0.8/com.sun.javafx.tk.RenderJob.run(RenderJob.java:58)
at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
at javafx.graphics@21.0.8/com.sun.javafx.tk.quantum.QuantumRenderer$PipelineRunnable.run(QuantumRenderer.java:126)
at java.base/java.lang.Thread.run(Thread.java:1583)

Response:
Checklist to resolve:

Canvas size:
Make sure you are using a safe, fixed size:

Only add the canvas once:
Do not add the same canvas to both the center and the VBox.
Use only:

Remove:

Test with minimal code:
Try running just this in your start method:

If this works, add your controls back one by one.

Summary:

Use a reasonable canvas size.
Only add the canvas to one parent/layout.
Test with minimal code to isolate the issue.
Let me know if you want a minimal working example or further help!


Project Reflection:
The way I structured my App file was to import all functions that were needed. Next, I created the class and created the observable list at the top, since it would be used by multiple functions later. The observable list was created to keep track of what shapes were created and list them out in the program. I then created the start method which creates how the GUI components are run from. I then created the canvas that would be needed as well as the labels and all of their associated functions. There could be an argument for creating the labels after the creating the radio buttons, but I figured that there would be no difference to how the program would function. As alluded to earlier, I created the radio buttons for the shapes and then a regular button for the clear function. The reason I had used separate buttons was to provide different aesthetics to the buttons. I also set the rectangle as default for the opening of the program so that the user could see what they needed to do to make the program work. I then created the button functions as well as created the vBox for the program. I chose the vBox over the hBox because I liked the look of the vBox a lot better than I did for the hBox. The next step was allowing the key board to be pressed to draw the shapes rather than clicking the buttons. This was the last creation in the method because it was an optional feature. Making sure the buttons were in working order was the top priority. I had also created a redraw method so that when the canvas was cleared, it would allow the shapes to be drawn again. The other classes were very similar to what we have used in the past. Drawable shape was the interface used since circle and rectangle were both drawable shapes. Thus, they used that inheritance to create their own draw and area methods. The DrawingController provided the basic outline of the canvas, like drawing shapes, clearing the canvas, and just having a canvas in general.


Feature Overview:
This program allows a user to draw a rectangle or a circle by clicking the radio buttons on the left side of the canvas. A "clear" button is also used to allow the user to clear the canvas and draw the shape again. They can also use their key board to draw by typing R for rectangle and C for circle. They can clear the canvas by typing X. Ther is also a total area section to show the area of both shapes. As more shapes are added, it will combine that area unless the canvas is cleared. there is also a shape counter which counted how many shapes were drawn. There is also a box that lists which shape the user had created.

Running progam:
To compile  the program, I used the javac command, along with the file path of the program with the different class files following after that.

PS C:\Users\user\Documents\GUI-s-2D-Graphics-Event-Handling-> javac --module-path  "C:\Users\user\Downloads\openjfx-21.0.8_windows-x64_bin-sdk\javafx-sdk-21.0.8\lib" --add-modules javafx.controls,javafx.fxml App.java CircleShape.java RectangleShape.java DrawableShape.java      

Levels 1, 2, and 3 should be completed with the project.

Running the program had the same format except useing java instaid of javac.

PS C:\Users\user\Documents\GUI-s-2D-Graphics-Event-Handling-> java --module-path "C:\Users\user\Downloads\openjfx-21.0.8_windows-x64_bin-sdk\javafx-sdk-21.0.8\lib" --add-modules javafx.controls,javafx.fxml -cp . App
