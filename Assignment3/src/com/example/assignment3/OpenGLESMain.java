package com.example.assignment3;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

public class OpenGLESMain extends Activity {
	
	private final String TAG = "OpenGLESMain";
	
	private GLSurfaceView mGLView;

	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private final float TRACKBALL_SCALE_FACTOR = 36.0f;
    private float mPreviousX;
    private float mPreviousY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Creates a GLSurfaceView instance and sets it as the ContentView for Activity
		mGLView = new MyGLSurfaceView(this);
		setContentView(mGLView);
	}
	

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
    	case R.id.action_pyramid:
    		Toast.makeText(OpenGLESMain.this,"Pyramid Clicked", Toast.LENGTH_SHORT).show();
    		MyRenderer.renderPyramid = true;
    		return true;
    	
    	case R.id.action_cube:
    		Toast.makeText(OpenGLESMain.this, "Cube Clicked", Toast.LENGTH_SHORT).show();
    		MyRenderer.renderCube = true;
    		return true;
    		
    	case R.id.action_cylindar:
    		Toast.makeText(OpenGLESMain.this, "Cylindar Clicked", Toast.LENGTH_SHORT).show();
            return true;
    	case R.id.action_trans:
    		Toast.makeText(OpenGLESMain.this,"Translate Clicked", Toast.LENGTH_SHORT).show();
    		return true;
    	
    	case R.id.action_rot:
    		Toast.makeText(OpenGLESMain.this, "Rotate Clicked", Toast.LENGTH_SHORT).show();
    		return true;
    		
    	case R.id.action_delete:
    		Toast.makeText(OpenGLESMain.this, "Delete Clicked", Toast.LENGTH_SHORT).show();
            return true;
    	case R.id.action_deleteall:
    		Toast.makeText(OpenGLESMain.this, "Delete All Clicked", Toast.LENGTH_SHORT).show();
            MyRenderer.renderPyramid = false;
    		MyRenderer.renderCube = false;
    		return true;
            
        default:
        	return super.onOptionsItemSelected(item);
		}
	}
	
	
	//pause the Activity and the GLSurfaceView
	@Override
	protected void onPause() {
		super.onPause(); 	// pause the Activity
		mGLView.onPause(); 	// pause the GLSurfaceView 
	}
	
	// when he Activity resumes focus
	@Override
	protected void onResume() {
		super.onResume();		// resume the Activity
		mGLView.onResume();		// resume the GLSurfaceView
	}

	class MyGLSurfaceView extends GLSurfaceView {

		public MyGLSurfaceView(Context context) {
			super(context);
			
			//Set the Renderer for drawing on the GLSurfaceView
			setRenderer(new MyRenderer());
			    
		}
	}

	
	 @Override 
	 public boolean onTrackballEvent(MotionEvent e) {
	        MyRenderer.mAngleX += e.getX() * TRACKBALL_SCALE_FACTOR;
	        MyRenderer.mAngleY += e.getY() * TRACKBALL_SCALE_FACTOR;
	        mGLView.requestRender();
	        return true;
	    }
	 
    @Override 
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dx = x - mPreviousX;
            float dy = y - mPreviousY;
            
            MyRenderer.mX = dx * TOUCH_SCALE_FACTOR;
            MyRenderer.mY = dy * TOUCH_SCALE_FACTOR;
            
            MyRenderer.mAngleX += dx * TOUCH_SCALE_FACTOR;
            MyRenderer.mAngleY += dy * TOUCH_SCALE_FACTOR;
            mGLView.requestRender();
            
        }
        mPreviousX = x;
        mPreviousY = y;
        
        
        return true;
    }
    
    
}
