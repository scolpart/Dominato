import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel
{
	static final long serialVersionUID = 1;
	
	private Partie partie;
	
	public Panneau(Partie partie)
	{
		this.partie = partie;
		this.setPreferredSize(new Dimension(20*partie.getPlateau().getTaille(), 45+20*partie.getPlateau().getTaille()));
		Controleur controleur = new Controleur(this, this.partie);
		this.addMouseListener(controleur);
		this.addMouseMotionListener(controleur);
		this.addMouseWheelListener(controleur);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	    
		for (int i = 0; i < this.partie.getPlateau().getTaille(); i++)
			for (int j = 0; j < this.partie.getPlateau().getTaille(); j++)
				if (!this.partie.getPlateau().get(i, j).getCouleur().equals(Couleur.VIDE) && !this.partie.getPlateau().get(i, j).getCouleur().equals(Couleur.PLEIN))
					this.partie.getPlateau().get(i, j).draw(g, 20*i, 20*j);
			
			
		if (!this.partie.estTerminee())
		{
			if (this.partie.dominoEstDansLeSens1())
				this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX(), this.partie.getY());
			else
				this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX(), this.partie.getY());
			
			if (this.partie.dominoEstHorizontal())
				if (this.partie.dominoEstDansLeSens1())
					this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX()+40, this.partie.getY());
				else
					this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX()+40, this.partie.getY());
			else
				if (this.partie.dominoEstDansLeSens1())
					this.partie.getDominoSelectionne().getMarque2().draw(g, this.partie.getX(), this.partie.getY()+40);
				else
					this.partie.getDominoSelectionne().getMarque1().draw(g, this.partie.getX(), this.partie.getY()+40);
	    }
		
		Font font = new Font("Rockwell", Font.PLAIN, 15);
		g.setFont(font);
		
		for (int k = 0; k < this.partie.getNbJoueurs(); k++)
		{
			if (this.partie.getJoueur(k).equals(this.partie.getJoueurCourant()))
			{
				if (!this.partie.estTerminee())
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.GREEN);
			}
		    else
		    	g.setColor(Color.BLACK);
		    
			g.drawString(this.partie.getJoueur(k).getNom()+" : "+this.partie.getJoueur(k).getScore()+" ("+this.partie.getJoueur(k).getJeu().size()+")", 20+k*this.getWidth()/this.partie.getNbJoueurs(), this.getHeight()-20);
		}
	}               
}