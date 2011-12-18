/*
 * This file is part of jCool.
 *
 * jCool is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright © 2011 Eneko Sanz Blanco <nkogear@gmail.com>
 *
 */

package jcool.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @author Eneko
 */
public class jReflectedImage extends JComponent {

    private BufferedImage image = null;

    public jReflectedImage(URL imageURL) {
        try {
            this.image = createReflectedImage(ImageIO.read(imageURL));
            this.setSize(image.getWidth(), image.getHeight());
            repaint();
        } catch (IOException ex) {
            Logger.getLogger("jcool").log(Level.SEVERE, "Couldn't load the image"
                                          + " from the URL.");
        }
    }

    public static BufferedImage createReflectedImage(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(),
                image.getHeight() * 6 / 4, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.scale(1.0, -1.0);
        g.drawImage(image, 0, -image.getHeight() * 2, null);
        g.scale(1.0, -1.0);
        g.translate(0, image.getHeight());
        GradientPaint mask = new GradientPaint(0, 0, new Color(1f, 1f, 1f, 0.5f),
                            0, image.getHeight() / 2, new Color(1f, 1f, 1f, 0f));
        g.setPaint(mask);
        g.setComposite(AlphaComposite.DstIn);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        return result;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(image, WIDTH, HEIGHT, null);
        g2.dispose();
    }

}
