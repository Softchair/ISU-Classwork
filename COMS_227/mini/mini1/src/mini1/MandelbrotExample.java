package mini1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import static mini1.LoopaholicsAnonymous.findEscapeCount;

/**
 * SEE THE LAST SECTION OF THE PDF FOR EXPLANATION:
 * 
 * This class is a GUI to try out the LoopaholicsAnonymous.escapeCount() method 
 * by rendering the Mandelbrot set.
 * 
 * @author smkautz
 */
public class MandelbrotExample extends JPanel
{
  /**
   * Window size.
   */
  private static final int WIDTH = 400;
  private static final int HEIGHT = 400;
  
  /**
   * Possible colors.
   */
  private static final Color[] COLORS = {Color.BLACK, Color.DARK_GRAY, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.YELLOW };

  /**
   * Some possible cutoff values for colors based on number of iterations.
   */
  private static final int[] CUTOFFS = {100, 90, 80, 70, 60, 50, 40};
  //private static final int[] CUTOFFS = {100, 30, 25, 20, 15, 10, 5};

  /**
   * Initial (x, y) region boundary.
   */
  private double left = -2.0;
  private double right = 1.0;
  private double bottom = -1.5;
  private double top = 1.5;

  /**
   * Mouse state.
   */
  private int mouseX;
  private int mouseY;
  private int mouseDownX;
  private int mouseDownY;
  private boolean pressed;
  
  /**
   * Cached results from computing escape value.
   */
  private int[][] cache = new int[WIDTH][HEIGHT];
  private boolean needsUpdate = true;
  
  /**
   * Constructor.
   */
  public MandelbrotExample()
  {
    addMouseListener(new MyMouseListener());
    addMouseMotionListener(new MyMouseMotionListener());
  }
  
  /**
   * Overridden paintComponent method is called by the Swing
   * framework when this component needs to be redrawn. 
   */
  @Override
  public void paintComponent(Graphics g)
  {
    // downcast the Graphics object
    Graphics2D g2 = (Graphics2D) g;
    
    // recalculate everything if region boundary has changed
    if (needsUpdate)
    {
      double x = left;
      double startX = left;
      double dx = (right - left) / WIDTH;
      double y = bottom;
      double dy = (top - bottom) / HEIGHT;

      for (int row = HEIGHT - 1; row >= 0; --row)
      {
        x = startX;
        for (int col = 0; col < WIDTH; ++col)
        {
          int iters = findEscapeCount(x, y, 100);
          
//          if (iters > 20 && iters < 90) 
//            System.out.println(x + " " + y + " " + iters);
          
          cache[col][row] = iters;
          x += dx;
        }
        y += dy;
      }
      needsUpdate = false;
    }
    
    // color points based on number of iterations
    for (int row = HEIGHT - 1; row >= 0; --row)
    {
      for (int col = 0; col < WIDTH; ++col)
      {
        Color c = getColor(cache[col][row]);
        
        // kludgy way to color a single pixel
        g2.setPaint(c);
        g2.drawLine(col,  row, col + 1, row + 1);
      }
    }
    
    // draw the rubberband
    if (pressed)
    {
      if (mouseDownX < mouseX && mouseDownY < mouseY)
      {
        g2.setPaint(Color.red);
        g2.drawRect(mouseDownX, mouseDownY, mouseX - mouseDownX, mouseY - mouseDownY);
      }
    }

  }

  /**
   * Determine a color for each pixel based on escape value.
   * @param iters
   * @return
   */
  private Color getColor(int iters)
  {
    for (int i = 0; i < COLORS.length; ++i)
    {
      if (iters >= CUTOFFS[i])
      {
        return COLORS[i];
      }
    }

    return Color.WHITE;
  }
  
  /**
   * Entry point. This method should normally do 
   * nothing except (possibly) parse command-line
   * arguments and invoke a helper method for creating
   * and starting up the UI.
   */
  public static void main(String[] args)
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        createAndShow();
      }
    };
    SwingUtilities.invokeLater(r);
  }
  
  /**
   * Static helper method creates a frame and
   * makes it visible.
   */
  private static void createAndShow()
  {
    JFrame frame = new JFrame();
    MandelbrotExample test = new MandelbrotExample();
    
    frame.getContentPane().add(test);    
    frame.setSize(WIDTH, HEIGHT);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
    // getWidth, getHeight only make sense after the frame is visible
    //System.out.println("Frame size " + frame.getWidth() + " x " + frame.getHeight());
    Insets insets = frame.getInsets();
    System.out.println("Border area: " + insets);   
  }
  
  /**
   * Mouse listener triggers calculation of new (x, y) region boundary.
   */
  private class MyMouseListener implements MouseListener
  {
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
      mouseDownX = event.getX();
      mouseDownY = event.getY();
      pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
      pressed = false;
      int tempX = event.getX();
      int tempY = event.getY();
      if (tempX > mouseDownX && tempY > mouseDownY)
      {
        // make the size of the new region the larger of the rubberbanded dimensions,
        // to keep the region square
        int dist = Math.max(tempX - mouseDownX, tempY - mouseDownY);
        
        // recalculate the boundaries, assumes WIDTH and HEIGHT are equal!
        double realDist = ((double) dist) / WIDTH * (right - left);  
        double newLeft = ((double) mouseDownX) / WIDTH * (right - left) + left;
        double newBottom = ((double) (HEIGHT - (mouseDownY + dist))) / HEIGHT * (top - bottom) + bottom;
        left  = newLeft;
        right = newLeft + realDist;
        bottom = newBottom;
        top = newBottom + realDist;
        
        System.out.println("left: " + left);
        System.out.println("right: " + right);
        System.out.println("bottom: " + bottom);
        System.out.println("top: " + top);
        needsUpdate = true;
        repaint();
      }   
    }
  }

  /**
   * MouseMotionListener event triggered if mouse is moved when
   * button pressed.
   */
  private class MyMouseMotionListener implements MouseMotionListener
  {

    @Override
    public void mouseDragged(MouseEvent event)
    {
      if (pressed)
      {
        // update state variables and trigger a repaint
        mouseX = event.getX();
        mouseY = event.getY();
        repaint();
      }
    }

    @Override
    public void mouseMoved(MouseEvent arg0)
    {
    }
    
  }
  
}
