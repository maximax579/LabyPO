package pl.edu.agh.po.lab03;

import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab05.GrassField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class World {
    public static final int ANIMATION_DELAY = 1000;

    public static void main(String[] args) {
        GrassField map = null;

        try {
            var directions = OptionsParser.parse(args);
            map = new GrassField(10);
            new Animal(map);
            new Animal(map,new Vector2d(3,4));
            map.run(directions);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        createAnimationWindow(map.getMapAnimator().getAnimation());
    }

    private static void createAnimationWindow(List<String> animation) {
        var frame = new JFrame("Zwierzęta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        var panel = new JPanel();
        frame.add(panel);

        var textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 25));
        textArea.setText(animation.stream().max(Comparator.comparing(String::length)).orElse(""));
        panel.add(textArea);

        var i = new AtomicInteger(1);
        ActionListener taskPerformer = evt -> {
            textArea.setText(animation.get(i.get() % animation.size()));
            i.addAndGet(1);
        };
        new Timer(ANIMATION_DELAY, taskPerformer).start();

        frame.pack();
        textArea.setText("");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
