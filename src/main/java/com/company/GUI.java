package com.company;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GUI extends JFrame {

    private JButton b1 = new JButton("Показать погоду");

    public GUI() {
        super("Погода в Санкт-Петербурге");
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        b1.addActionListener(new Pars());
        container.add(b1);
    }


    class Pars implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String url = "https://pogoda.spb.ru" ;
            try {
                Document info = Jsoup.parse(new URL(url),3000);
                Element table = info.select("table[class = wt]").first();

                Element v1 = table.select("tr[class=wth]").first();
                Element v2 = v1.select("th[id = dt]").first();
                Element v3 = table.select("tr[valign = top]").first();
                String date = v2.text();
                String msg = v3.text();


               JOptionPane.showMessageDialog(null, msg, date, JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, "Произошла ошибка!", "Информация:", JOptionPane.PLAIN_MESSAGE);

            }
        }
    }
}
