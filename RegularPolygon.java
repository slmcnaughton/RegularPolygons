/* Regular Polygon.java
 * 
 * This project attempts to visually represents a regular n-sided polygon by
 * taking an input of the sides and radius and creating a visual of the polygon 
 * on screen. It outputs the visual of the polygon, the area, and the
 *  perimeter.
 *
 * @Author Seth McNaughton
 * Date Created: 4/27/2014
 */


import java.lang.*;		import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class RegularPolygon
{
	public static void main (String [] args)
	{

		ActionEventFrame f = new ActionEventFrame ("Polygons", 1000,600);
	}
}

class ActionEventFrame extends JFrame
{
	MyPolygonPanel panel = new MyPolygonPanel(6);		//Start with a hexagon

	public ActionEventFrame(String name, int h, int w)
    {
        setTitle (name);
        setSize (h, w);
                
		add (panel, BorderLayout.CENTER );

		setVisible (true);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}


/**
 * This class creates a polygon and draws it on screen. The default polygon is a hexagon.
 */
class MyPolygonPanel extends JPanel
{
	int numberOfSides = 6;	//Start with a hexagon
	double radius = 150.0;
	Polygon p = new Polygon (numberOfSides, radius);
	double rotation = 0;
	boolean roundOn = false;

	
	JPanel leftPanel = new JPanel () ;
		JPanel fillPanel = new JPanel ();
			JLabel fillLabel = new JLabel ("\n\n");
		JPanel sidesPanel = new JPanel (new GridLayout(1,4) );
			JLabel numSides = new JLabel ("Sides: ");
			JButton subtractSides = new JButton ("-");
			JTextField numberSides = new JTextField (" " + numberOfSides + " ");
			JButton addSides = new JButton ("+");
		JPanel radiusPanel = new JPanel (new GridLayout(1,4)  );
			JButton subtractRad = new JButton ("-");
			JLabel rad = new JLabel ("Radius: ");
			JTextField radSize = new JTextField (" " + radius + " ");
			JButton addRad = new JButton ("+");
		JPanel sliderPanel = new JPanel(new GridLayout(1,1) ) ;
			JLabel rot = new JLabel ("Rotation: ");
			JSlider mySlider = new JSlider(JSlider.HORIZONTAL, -360, 360, 0);
		JPanel roundingPanel = new JPanel (new GridLayout(1,1) );
			JToggleButton roundButton = new JToggleButton (" Rounding: Off ", false);
		
		JPanel fillPanel2 = new JPanel ();
			JLabel fillLabel2 = new JLabel ("\n\n");
		JPanel polyAreaPanel = new JPanel();
			JLabel polyArea = new JLabel("Polygon Area: " + p.getArea() + " un");
		JPanel polyPerimPanel = new JPanel() ;
			JLabel polyPerim = new JLabel("Polygon Perimeter: " 
												+ p.getPerimeter() + " sq un");
		JPanel fillPanel3 = new JPanel ();
			JLabel fillLabel3 = new JLabel ("\n\n");

	
	public MyPolygonPanel (int n)
	{
		setLayout(new BorderLayout() );
		numberOfSides = n;
		Font f = new Font ("Times", Font.BOLD, 20);

		add (leftPanel, BorderLayout.WEST);

		leftPanel.setLayout(new BoxLayout (leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.add(fillPanel);
			fillPanel.setMaximumSize(new Dimension (300, 200));	
			fillPanel.add(fillLabel);
				fillLabel.setFont (f);
		leftPanel.add(sidesPanel);
			sidesPanel.setMaximumSize(new Dimension (350, 50));
			sidesPanel.add(numSides);
				numSides.setFont(f);
      			numSides.setHorizontalAlignment(SwingConstants.RIGHT);
			SidesListener sl = new SidesListener ();
			sidesPanel.add(subtractSides);
				subtractSides.setFont (f);
				subtractSides.setForeground (Color.RED );
				subtractSides.addActionListener (sl);
			EnterListener2 e2 = new EnterListener2 ();
			sidesPanel.add(numberSides);
				numberSides.setFont(f);
        		numberSides.setForeground(Color.BLUE);
       			numberSides.setHorizontalAlignment (JTextField.CENTER);
       			numberSides.addKeyListener (e2);
			sidesPanel.add(addSides);
				addSides.setFont (f);
				addSides.setForeground (Color.GREEN);
				addSides.addActionListener (sl);
		leftPanel.add(radiusPanel);
			radiusPanel.setMaximumSize(new Dimension (350, 50));
			radiusPanel.add(rad);
				rad.setFont(f);
      			rad.setHorizontalAlignment(SwingConstants.RIGHT);
			RadListener radl = new RadListener ();
			EnterListener el = new EnterListener ();
			radiusPanel.add(subtractRad);
				subtractRad.setFont (f);
				subtractRad.setForeground (Color.RED );
				subtractRad.addActionListener (radl);
			radiusPanel.add(radSize);
				radSize.setFont(f);
        		radSize.setForeground(Color.BLUE);
        		radSize.setHorizontalAlignment (JTextField.CENTER);
        		radSize.addKeyListener (el);
			radiusPanel.add(addRad);
				addRad.setFont (f);
				addRad.setForeground (Color.GREEN);
				addRad.addActionListener (radl);
		leftPanel.add(sliderPanel);
			sliderPanel.setMaximumSize(new Dimension (300, 50));
			sliderPanel.add(rot);
				rot.setFont(f);
				rot.setHorizontalAlignment(SwingConstants.RIGHT);
			RotListener r1 = new RotListener();
			sliderPanel.add(mySlider);
				mySlider.addChangeListener (r1);
		leftPanel.add(roundingPanel);
			roundingPanel.setMaximumSize(new Dimension (300, 50));
			RoundingListener round1 = new RoundingListener();
			roundingPanel.add(roundButton);
				roundButton.setFont (f);
				roundButton.addItemListener(round1);
		leftPanel.add(fillPanel2);
			fillPanel2.setMaximumSize(new Dimension (300, 1500));	
			fillPanel2.add(fillLabel2);
				fillLabel2.setFont (f);
		leftPanel.add(polyAreaPanel);
			polyAreaPanel.setMaximumSize(new Dimension (500, 100));	
			polyAreaPanel.add(polyArea);
				polyArea.setFont(f);
				polyArea.setHorizontalAlignment(SwingConstants.LEFT);
				leftPanel.add(polyAreaPanel);
		leftPanel.add(polyPerimPanel);
			polyPerimPanel.setMaximumSize(new Dimension (500, 100));	
			polyPerimPanel.add(polyPerim);
				polyPerim.setFont(f);
				polyPerim.setHorizontalAlignment(SwingConstants.LEFT);
		leftPanel.add(fillPanel3);
			fillPanel3.setMaximumSize(new Dimension (300, 200));	
			fillPanel3.add(fillLabel);
				fillLabel3.setFont (f);
		
	
	}


	/**
	 *  An inner class for the rounding toggleButton
	 */
	class RoundingListener implements ItemListener
	{ 
		public void itemStateChanged (ItemEvent e)
		{
			if (roundButton.isSelected () )
			{
				roundButton.setText(" Rounding: On ");
					polyArea.setText ("Polygon Area: " + 
							((double)Math.round(p.getArea() * 100) / 100) + " sq un");
					polyPerim.setText ("Polygon Perimeter: " + 
							((double)Math.round(p.getPerimeter() * 100) / 100) + " un");
				roundOn = true;

			}
			else
			{
				roundButton.setText(" Rounding: Off ");
				polyArea.setText ("Polygon Area: " + p.getArea() + " sq un");
				polyPerim.setText ("Polygon Area: " + p.getPerimeter() + " un");
				roundOn = false;
			}
			repaint();
		}

	}
	/**
	 *  An inner class to detect updates with the "enter" key
	 */
	class EnterListener2 implements KeyListener 
	{ 
		public void keyReleased (KeyEvent e ) 
		{ 
			if (e.getKeyCode() == 10)
				try
				{
					String textSides = numberSides.getText();
					numberOfSides = Integer.parseInt(textSides);
			
					if (radius < 0) radius = 0;	//Min. radius
					radSize.setText (" " + radius + " ");

					if (numberOfSides < 3) numberOfSides = 3;	//Min. # of sides
					numberSides.setText (" " + numberOfSides + " ");

					p.setNumberOfSides(numberOfSides);
					p.setRadius(radius);
					if (roundOn == true)
					{
						polyArea.setText ("Polygon Area: " + 
									((double)Math.round(p.getArea() * 100) / 100)+ " sq un" );
						polyPerim.setText ("Polygon Perimeter: " + 
									((double)Math.round(p.getPerimeter() * 100) / 100) + " un");

					}
					else
					{
						polyArea.setText ("Polygon Area: " + p.getArea() + " sq un");
						polyPerim.setText ("Polygon Area: " + p.getPerimeter() + " un");
					}
				repaint();
			}
			finally{}
	
		}
		public void keyPressed(KeyEvent e) 
		{
		}
		public void keyTyped(KeyEvent e) 
		{
		}


	}


	/**
	 *  An inner class to detect updates with the "enter" key
	 */
	class EnterListener implements KeyListener 
	{ 
		public void keyReleased (KeyEvent e ) 
		{ 
			if (e.getKeyCode() == 10)
			{
				//String textSides = numberSides.getText();
				//numberOfSides = Integer.parseInt(textSides);
				String textRadius = radSize.getText();
				radius = Double.parseDouble(textRadius);

				if (radius < 0) radius = 0;	//Min. radius
				radSize.setText (" " + radius + " ");

				if (numberOfSides < 3) numberOfSides = 3;	//Min. # of sides
				numberSides.setText (" " + numberOfSides + " ");

				p.setNumberOfSides(numberOfSides);
				p.setRadius(radius);
				if (roundOn == true)
				{
					polyArea.setText ("Polygon Area: " + 
								((double)Math.round(p.getArea() * 100) / 100)+ " sq un" );
					polyPerim.setText ("Polygon Perimeter: " + 
								((double)Math.round(p.getPerimeter() * 100) / 100) + " un");

				}
				else
				{
					polyArea.setText ("Polygon Area: " + p.getArea() + " sq un");
					polyPerim.setText ("Polygon Area: " + p.getPerimeter() + " un");
				}
				repaint();
			}
	
		}
		public void keyPressed(KeyEvent e) 
		{
		}
		public void keyTyped(KeyEvent e) 
		{
		}


	}

	/**
	 *  An inner class for the rotation slider
	 */
	class RotListener implements ChangeListener
	{ 
		public void stateChanged (ChangeEvent e)
		{
			int value = mySlider.getValue();
			rotation = Math.toRadians(value);
			repaint();
		}

	}

	/**
	 *  An inner class for the radius + and - button. Changes the number of sides and 
	 * repaints the polygon on screen.
	 */
	class RadListener implements ActionListener 
	{ 
		public void actionPerformed (ActionEvent e) 
		{ 
			if (e.getSource() == addRad)
			{
				radius++;
			}
			else if (e.getSource() == subtractRad) 
			{
				radius-- ;
			}
			if (radius < 0) radius = 0;	//Min. radius
			radSize.setText (" " + radius + " ");
			p.setRadius(radius);

			if (roundOn == true)
			{
				polyArea.setText ("Polygon Area: " + 
							((double)Math.round(p.getArea() * 100) / 100)+ " sq un" );
				polyPerim.setText ("Polygon Perimeter: " + 
							((double)Math.round(p.getPerimeter() * 100) / 100) + " un");

			}
			else
			{
				polyArea.setText ("Polygon Area: " + p.getArea() + " sq un");
				polyPerim.setText ("Polygon Area: " + p.getPerimeter() + " un");
			}
			
			repaint();
			
		}
	}
			
	/**
	 *  An inner class for the + and - button. Changes the number of sides and 
	 * repaints the polygon on screen.
	 */
	class SidesListener implements ActionListener 
	{ 
		public void actionPerformed (ActionEvent e) 
		{ 
			if (e.getSource() == addSides)
			{
				numberOfSides++;
			}
			else if (e.getSource() == subtractSides) 
			{
				numberOfSides-- ;
			}
			if (numberOfSides < 3) numberOfSides = 3;	//Min. # of sides
			numberSides.setText (" " + numberOfSides + " ");
			p.setNumberOfSides(numberOfSides);
			if (roundOn == true)
			{
				
				polyArea.setText ("Polygon Area: " + 
							((double)Math.round(p.getArea() * 100) / 100) + " sq un");
				polyPerim.setText ("Polygon Perimeter: " + 
							((double)Math.round(p.getPerimeter() * 100) / 100) + " un");
			}
			else
			{
				polyArea.setText ("Polygon Area: " + p.getArea() + " sq un");
				polyPerim.setText ("Polygon Area: " + p.getPerimeter() + " un");
			}
			repaint();
			
		}
	}

	/**
	 * Method to paint the polygon by drawing lines from the center to each vertex, and 
	 * connecting each vertex to the previously drawn vertex.
	 */
	public void paintComponent (Graphics g2)
	{
		super.paintComponent (g2);
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int xOrigin = 675;		
		int yOrigin = 250;

		double exteriorAngle = Math.toRadians(360.0 / numberOfSides);	//Each Vertex Angle
		int xHold = (int)(xOrigin + radius * Math.cos(rotation));
		int yHold = (int)(yOrigin + radius * Math.sin(rotation));

		
		
		for(int i = 1; i <= numberOfSides; i++)
		{
			double theta = exteriorAngle * i;
			double xEnd = xOrigin + radius * Math.cos(theta + rotation);
			double yEnd = yOrigin + radius * Math.sin(theta + rotation);
			g.drawLine (xOrigin, yOrigin, (int)xEnd, (int)yEnd);
			g.drawLine ( (int)xEnd, (int)yEnd, xHold, yHold);
			xHold = (int)xEnd;
			yHold = (int)yEnd;
		}

	}
}

class Polygon
{
	private int n;		//number of sides
	private double r;			// radius
	
	/**
	 * Constructor to create a polygon of n sides with a default radius of 1.
	 */
	public Polygon (int sides)
	{
		n = sides;
		r = 1;	//default radius of a circle
	}

	/**
	 * Constructor to create a polygon of n sides and radius r.
	 */
	public Polygon (int sides, double radius)
	{
		n = sides;
		r = radius;
	}

	/**
	 * Getter method to return the number of sides of the polygon.
	 */
	public int getNumberOfSides()
	{
		return n;
	}

	/**
	 * Getter method to return the radius of the polygon.
	 */
	public double getRadius()
	{
		return r;
	}

	/**
	 * Setter method to change the number of sides of the polygon.
	 */
	public void setNumberOfSides(int sides)
	{
		n = sides;
	}

	/**
	 * Setter method to set the radius of the polygon.
	 */
	public void setRadius(double radius)
	{
		r = radius;
	}

	/**
	 *  Returns the integer sum of the interior angles using the formula (n-2)*180.
	 */
	public int getAngleSum()
	{
		return (n-2)*180;
	}

	/** 
	 * Returns the double value of each interior angle by taking the sum of the interior
	 * angles (getAngleSum()) and dividing by the number of sides.
	 */
	public double getEachAngle()
	{
		double sum = getAngleSum();
		return sum / n;
	}


	/**
	 * Returns the apothem of the regular polygon.
	 * This is found by creating an isosceles triangle with the base as one the side of the
	 * polygon, and the legs are radii. Base angles are equal to eachAngle / 2. The apothem is the 
	 * perpendicular bisector of the triangle.
	 */
	public double getApothem()
	{
		double angleDegrees = getEachAngle() / 2;
		double angleInRadians = Math.toRadians(angleDegrees);

		return r * Math.sin(angleInRadians);
	}

	/**
	 * Returns the side length of the polygon.
	 * When an isosceles triangle is created with the base of the triangle as the side of the
	 * polygon, the legs are radii. When the perpendicular bisector of the base is drawn, a 
	 * right triangle is formed with the hypotenuse being the radius. Using right triangle
	 * trigonometry, and the base angle, the side length can be found using 2*r*cos(theta).
	 * 
	 */
	public double getSideLength()
	{
		double angleDegrees = getEachAngle() / 2;
		double angleInRadians = Math.toRadians(angleDegrees);

		return 2 * r * Math.cos(angleInRadians);
	}


	/**
	 * Returns the perimeter of the polygon
	 */
	public double getPerimeter()
	{
		double s = getSideLength();
		return n*s;
	}


	/**
	 * Returns the area of the polygon by using the formula A = 1/2*a*p, where A is Area,
	 * a is the apothem (from the getApothem method) and p is the perimeter (from getPerimeter).
	 */
	public double getArea()
	{
		double a = getApothem();
		double p = getPerimeter();
		return 0.5 * a * p;
	}
}
