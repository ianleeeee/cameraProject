package cameraProject;

import java.io.IOException;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class Pictures {
	int[] pixels;

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			Pictures test = new Pictures();
			System.out.println(test.getRedPercentage());
		} catch (FailedToRunRaspistillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Pictures() throws FailedToRunRaspistillException, IOException, InterruptedException {
		RPiCamera cam = new RPiCamera("/home/pi/Desktop");
		cam.setWidth(50).setHeight(50);
		pixels = cam.takeStillAsRGB(50, 50, false);
		//cam.takeStill("pic.jpg");

		//for (int i : pixels) {
			//System.out.println(i);
		//}
	}

	public double getRedPercentage() {
		// returns the percentage of red pixels / total pixels
		int numPixels = (pixels.length) / 3;
		boolean[] redPixels = new boolean[numPixels];
		for (int i = 0; i < pixels.length; i += 3) {
			int[] currentPixel = new int[3];
			for (int j = 0; j < 3; j++) {
				currentPixel[j] = pixels[i + j];
			}
			int red = currentPixel[0];
			int green = currentPixel[1];
			int blue = currentPixel[2];
			int biggest = 0;
			if (blue >= green) {
				biggest = blue;
			} else {
				biggest = green;
			}
			int actualRed = red - biggest;
			// is 25 a good threshold??
			if (actualRed < 15) {
				redPixels[i / 3] = false;
			} else {
				redPixels[i / 3] = true;
			}
		}
		int trueRedPixels = 0;
		for (boolean pixel : redPixels)
		{
			if (pixel) {
				trueRedPixels++;
			}
		}

		double redPercentage = (double)trueRedPixels / numPixels;
	//	redPercentage = Math.round(redPercentage * 100);
		return redPercentage;
	}
}
