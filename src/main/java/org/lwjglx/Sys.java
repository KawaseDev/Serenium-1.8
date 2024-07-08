package org.lwjglx;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class Sys {
	
	static {
		if (!GLFW.glfwInit())
			throw new IllegalStateException("Unable to initialize glfw");
	}
	
	public static void initialize() {
		
	}

	/** Returns the LWJGL version. */
	public static String getVersion() {
		return Version.getVersion();
	}
	
	/**
	 * Obtains the number of ticks that the hires timer does in a second. This method is fast;
	 * it should be called as frequently as possible, as it recalibrates the timer.
	 *
	 * @return timer resolution in ticks per second or 0 if no timer is present.
	 */
	public static long getTimerResolution() {
		return 1000;
	}

	/**
	 * Gets the current value of the hires timer, in ticks. When the Sys class is first loaded
	 * the hires timer is reset to 0. If no hires timer is present then this method will always
	 * return 0.<p><strong>NOTEZ BIEN</strong> that the hires timer WILL wrap around.
	 *
	 * @return the current hires time, in ticks (always >= 0)
	 */
	public static long getTime() {
		return System.currentTimeMillis();
	}
	
	public static long getNanoTime() {
		return System.nanoTime();
	}
	
	public static boolean openURL(String url) {
		if (!Desktop.isDesktopSupported()) return false;

		Desktop desktop = Desktop.getDesktop();
		if (!desktop.isSupported(Desktop.Action.BROWSE)) return false;

		try {
			desktop.browse(new URI(url));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static void alert(String title, String message) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			LWJGLUtil.log("Caught exception while setting LAF: " + e);
		}
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	}
}