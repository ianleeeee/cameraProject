package cameraProject;

import java.io.IOException;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class pictures {
	int [] pixels;
	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			new pictures();
		} catch (FailedToRunRaspistillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	pictures() throws FailedToRunRaspistillException, IOException, InterruptedException{
		RPiCamera cam = new RPiCamera();
		pixels = cam.takeStillAsRGB(25,25,false);	
		//for(int i : pixels){
			//System.out.println(i);
		//}
	}
	public double findRed(){
		//returns the percentage of red pixels / total pixels
		int numPixels = (pixels.length)/3;
		for (int i = 0; i < numPixels; i++) {
			int run = 0;
			int[] currentPixel = new int[3];
			for(int val :pixels){
				if(run%3==0){
					run = 0;
				}
				currentPixel[run] = val;
			}
			int red = currentPixel[0];
			int green = currentPixel[1];
			int blue = currentPixel[2];
			int biggest = 0;
			if(blue>= green){
				biggest = blue;
			}
			else{
				biggest  = green;
			}
		}
		return 0;
	}
	
}
