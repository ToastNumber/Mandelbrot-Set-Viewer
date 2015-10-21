package toastNumber.complexsets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ComplexSetView extends JPanel implements Observer, MouseWheelListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private boolean colorful;

	private Point2D.Double frameBottomRight;
	private Point2D.Double frameTopLeft;
	private ComplexSetModel model;
	private double zoomScale = 1.0;

	public ComplexSetView(ComplexSetModel model) {
		super();
		this.model = model;
		zoomScale = 1.0;
		frameTopLeft = new Point2D.Double(0.0, 0.0);
		frameBottomRight = new Point2D.Double(1.0, 1.0);
		colorful = false;
		
		addMouseWheelListener(this);
		addMouseListener(this);
	}

	private void fixFrame(MouseEvent e) {
		int width = getWidth();
		int height = getHeight();
		double xR = (double) e.getX() / width;
		double yR = (double) e.getY() / height;

		double currentFrameWidth = frameBottomRight.getX() - frameTopLeft.getX();
		double currentFrameHeight = frameBottomRight.getY() - frameTopLeft.getY();
		double newFrameLength = 1 / zoomScale;

		double minX = Math.max(0.0, (frameTopLeft.getX() + xR * currentFrameWidth) - xR * newFrameLength);
		double maxX = Math.min(1.0, (frameTopLeft.getX() + xR * currentFrameWidth) + (1 - xR) * newFrameLength);
		double minY = Math.max(0.0, (frameTopLeft.getY() + yR * currentFrameHeight) - yR * newFrameLength);
		double maxY = Math.min(1.0, (frameTopLeft.getY() + yR * currentFrameHeight) + (1 - yR) * newFrameLength);

		frameTopLeft.setLocation(minX, minY);
		frameBottomRight.setLocation(maxX, maxY);
	}

	public Color getColor(int numIterations, int maxIterations) {
		if (colorful) {
			double ratio = (double) numIterations / maxIterations;
			if (ratio < (double) 1 / 3) {
				return new Color((int) (ratio * 255), (int) (ratio * 126), (int) (ratio * 765));
			} else if (ratio < (double) 9 / 12) {
				return new Color((int) (ratio * 170), (int) (ratio * 255), (int) (ratio * 85));
			} else if (ratio < 1) {
				return new Color((int) (ratio * 255), (int) (ratio * 85), (int) (ratio * 42));
			} else {
				return new Color(0, 0, 0);
			}
		} else {
			int val = 255 - (int) (255 * (double) numIterations / maxIterations);
			return new Color(val, val, val);
		}
	}

	public boolean isColorful() {
		return colorful;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			zoom(-1);
			fixFrame(e);
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			zoom(1);
			fixFrame(e);
		}
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseClicked(arg0);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		zoom(e.getWheelRotation());
		
		if (zoomScale == 1.0) {
			resetView();
		} else {
			fixFrame(e);
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();

		g2.clearRect(0, 0, width, height);

		double xRange = model.getMaxX() - model.getMinX();
		double yRange = model.getMaxY() - model.getMinY();
		double minX = model.getMinX() + frameTopLeft.getX() * xRange;
		double maxX = model.getMinX() + frameBottomRight.getX() * xRange;
		double minY = model.getMinY() + frameTopLeft.getY() * yRange;
		double maxY = model.getMinY() + frameBottomRight.getY() * yRange;
		int maxIterations = model.getCurrentNumIterations();

		for (int i = 0; i < width; ++i) {
			double x = minX + (maxX - minX) * i / width;
			for (int j = 0; j < height; ++j) {
				int iterations = model.getNumIterationsToFail(x, minY + j * (maxY - minY) / height, maxIterations);
				g2.setColor(getColor(iterations, maxIterations));
				g2.fillRect(i, j, 1, 1);
			}
		}
	}

	public void resetView() {
		frameTopLeft.setLocation(0.0, 0.0);
		frameBottomRight.setLocation(1.0, 1.0);
		zoomScale = 1.0;
		repaint();
	}

	public void setColorful(boolean flag) {
		colorful = flag;
		repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	private void zoom(int direction) {
		if (direction < 0) {
			zoomScale = Math.max(1.0, zoomScale + zoomScale * 0.25);
		} else if (direction > 0) {
			zoomScale = Math.max(1.0, zoomScale - zoomScale * 0.25);
		}
	}
}
