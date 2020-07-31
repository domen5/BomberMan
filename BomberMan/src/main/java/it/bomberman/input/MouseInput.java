package it.bomberman.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput  implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx= e.getX();
		int my= e.getY();
		//public Rectangle playButton = new Rectangle(740, 200, 250, 50);
		//public Rectangle settingsButton = new Rectangle(740, 300, 250, 50);
		//public Rectangle exitButton = new Rectangle(740, 400, 250, 50); 
		//Press Play

		if(mx>=740 && mx<990  && my>=200 && my<250)
		{
			//STATE = GAME
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
