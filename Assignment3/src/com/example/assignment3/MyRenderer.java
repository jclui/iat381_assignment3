package com.example.assignment3;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class MyRenderer implements GLSurfaceView.Renderer {
	
	Context context;
	
	private Pyramid pyramid = new Pyramid();
	private Cube cube = new Cube();
	private Rect rect = new Rect();
	
	private static float anglePyramid = 0; // Rotational angle in degree for pyramid 
	private static float angleCube = 0;    // Rotational angle in degree for cube 
    private static float speedPyramid = 2.0f; // Rotational speed for pyramid 
    private static float speedCube = -1.5f;   // Rotational speed for cube 

	public static float mAngleX;
	public static float mAngleY;
    
	public static boolean renderPyramid = false;
	public static boolean renderCube = false;
	@Override
	// Called when the surface is first created or recreated.
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		// Set the background frame color
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		
		// Set depth's clear-value
		gl.glClearDepthf(1.0f);
		
		// Enables depth-buffer for hidden surface removal
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		// The type of depth testing
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		// Perspective view
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		// Enable smooth color shading
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		// Disables Dither
		gl.glDisable(GL10.GL_DITHER);
	}

	@Override
	// Called to draw the current frame
	public void onDrawFrame(GL10 gl) {
		// Redraw background color
		gl.glClear(GLES10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		if (renderPyramid == true) {
		
		// ----- Render the Pyramid -----
	      gl.glLoadIdentity();                 // Reset the model-view matrix
	      gl.glTranslatef(-3.5f, 0.0f, -8.0f); // Translate left and into the screen
	      //gl.glRotatef(anglePyramid, 0.1f, 1.0f, -0.1f); // Rotate (NEW)
	      gl.glRotatef(mAngleX, 0, 1, 0);
          gl.glRotatef(mAngleY, 1, 0, 0);
	      pyramid.draw(gl);                             // Draw the pyramid (NEW)
		}
		
		if (renderCube == true) {
	      // ----- Render the Color Cube -----
	      gl.glLoadIdentity();                // Reset the model-view matrix
	      gl.glTranslatef(0.0f, 0.0f, -8.0f); // Translate right and into the screen
	      gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
	      //gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
	      gl.glRotatef(mAngleX, 0, 1, 0);
          gl.glRotatef(mAngleY, 1, 0, 0);
	      cube.draw(gl);                      // Draw the cube (NEW)
		}
		
	
	      // ----- Render the Color Cube -----
	      gl.glLoadIdentity();                // Reset the model-view matrix
	      gl.glTranslatef(3.5f, 0.0f, -8.0f); // Translate right and into the screen
	      gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
	      //gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
	      gl.glRotatef(mAngleX, 0, 1, 0);
          gl.glRotatef(mAngleY, 1, 0, 0);
	      rect.draw(gl);                      // Draw the cube (NEW)
			
	      // Update the rotational angle after each refresh (NEW)
	       anglePyramid += speedPyramid;   // (NEW)
	       angleCube += speedCube;         // (NEW)
	}

	@Override
	// Called when the surface is first displayed and after window's size changes.
	// Used to set the view port and projection mode.  
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
		if (height == 0) height = 1;   // To prevent divide by zero
	      float aspect = (float)width / height;
	   
	      // Set the viewport (display area) to cover the entire window
	      gl.glViewport(0, 0, width, height);
	  
	      // Setup perspective projection, with aspect ratio matches viewport
	      gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
	      gl.glLoadIdentity();                 // Reset projection matrix
	      
	      // Use perspective projection
	      GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
	  
	      gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
	      gl.glLoadIdentity();                 // Reset
	  
	}


}
