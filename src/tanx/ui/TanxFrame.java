package tanx.ui;

import tanx.model.GameMap;

import javax.swing.*;

public class TanxFrame extends JFrame {
    public TanxFrame(GameMap model) {
        super("Tanx");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new GameCanvas(model));

        setSize(300, 200);
    }
}
