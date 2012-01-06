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

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Static utility methods. Should be used within the EDT.
 * 
 * @author Eneko
 */
public class JCoolUtils {
    
    private static Font jCoolFont;
    private static Font jCoolBoldFont;
    
    static {
        try {
            jCoolBoldFont = Font.createFont(Font.TRUETYPE_FONT, JCoolUtils.class
                   .getResourceAsStream("/jcool/resources/DroidSans-Bold.ttf"));
            jCoolBoldFont = jCoolBoldFont.deriveFont(13f);
            jCoolFont = Font.createFont(Font.TRUETYPE_FONT, JCoolUtils.class
                        .getResourceAsStream("/jcool/resources/DroidSans.ttf"));
            jCoolFont = jCoolFont.deriveFont(14f);
        } catch (Exception ex) {
            Logger.getLogger("jcool").log(Level.SEVERE, "Couldn't load fonts.");
        }
    }

    /**
     * Tries to set the font to the whole swing interface. However, some L&F
     * ommit font settings and use their own one. This usually happens with
     * system L&Fs, which use the system's fonts. Therefore, it's not recommended
     * to use it. But you can still change the JCool components default font
     * with the methods this class provides. The rest swing JComponents will
     * retain their font.
     *
     * @param f
     */
    public static void setUIFont (Font f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        FontUIResource fontResource = new FontUIResource(f);
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
                if (value != null && value instanceof FontUIResource)
                    UIManager.put (key, fontResource);
        }
        UIManager.put("defaultFont", fontResource);
    }

    public static Font getJCoolFont() {
        return jCoolFont;
    }
    
    public static Font getJCoolBoldFont() {
        return jCoolBoldFont;
    }
    
    public static void setJCoolFont(Font newFont) {
        jCoolFont = newFont;
    }
    
    public static void setJCoolBoldFont(Font newFont) {
        jCoolBoldFont = newFont;
    }
    
}
