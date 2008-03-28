/*
 * ColorScheme.java
 *
 * Created on 20.02.2005, 21:20
 * Copyright (c) 2006-2007, Daniel Apatin (ad), http://apatin.net.ru
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * You can also redistribute and/or modify this program under the
 * terms of the Psi License, specified in the accompanied COPYING
 * file, as published by the Psi Project; either dated January 1st,
 * 2005, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package Colors;
//#ifdef COLORS
//# import io.NvStorage;
//# import java.io.DataInputStream;
//# import java.io.DataOutputStream;
//# import java.io.IOException;
//# import midlet.*;
//#endif

public class ColorScheme {
    
//#if NICK_COLORS
//#     static int strong(int color) {
//#         if (color==instance.MESSAGE_IN) {
//#             return instance.MESSAGE_IN_S;
//#         } else if (color==instance.MESSAGE_OUT) {
//#             return instance.MESSAGE_OUT_S;
//#         } else if (color==instance.MESSAGE_PRESENCE) {
//#             return instance.MESSAGE_PRESENCE_S;
//#         }
//#         return color;
//#     }
//#endif

//#ifdef COLORS
//#     static Colors instance=Colors.getInstance();
//#     
//#     public static void loadFromStorage(){
//# 	try {
//# 	    DataInputStream inputStream=NvStorage.ReadFileRecord("ColorDB", 0);
//#             instance.BALLOON_INK=inputStream.readInt();
//#             instance.BALLOON_BGND=inputStream.readInt();
//#             instance.LIST_BGND=inputStream.readInt();
//#             instance.LIST_BGND_EVEN=inputStream.readInt();
//#             instance.LIST_INK=inputStream.readInt();
//#             instance.MSG_SUBJ=inputStream.readInt();
//#             instance.MSG_HIGHLIGHT=inputStream.readInt();
//#             instance.DISCO_CMD=inputStream.readInt();
//#             instance.BAR_BGND=inputStream.readInt();
//#             instance.BAR_INK=inputStream.readInt();
//#             instance.CONTACT_DEFAULT=inputStream.readInt();
//#             instance.CONTACT_CHAT=inputStream.readInt();
//#             instance.CONTACT_AWAY=inputStream.readInt();
//#             instance.CONTACT_XA=inputStream.readInt();
//#             instance.CONTACT_DND=inputStream.readInt();
//#             instance.GROUP_INK=inputStream.readInt();
//#             instance.BLK_INK=inputStream.readInt();
//#             instance.BLK_BGND=inputStream.readInt();
//#             instance.MESSAGE_IN=inputStream.readInt();
//#             instance.MESSAGE_OUT=inputStream.readInt();
//#             instance.MESSAGE_PRESENCE=inputStream.readInt();
//#             instance.MESSAGE_AUTH=inputStream.readInt();
//#             instance.MESSAGE_HISTORY=inputStream.readInt();
//#             instance.PGS_REMAINED=inputStream.readInt();
//#             instance.PGS_COMPLETE=inputStream.readInt();
//#             inputStream.readInt();//PGS_BORDER=inputStream.readInt();
//#             inputStream.readInt();//PGS_BGND=inputStream.readInt();
//#             instance.HEAP_TOTAL=inputStream.readInt();
//#             instance.HEAP_FREE=inputStream.readInt();
//#             instance.CURSOR_BGND=inputStream.readInt();
//#             instance.SCROLL_BRD=inputStream.readInt();
//#             instance.SCROLL_BAR=inputStream.readInt();
//#             instance.SCROLL_BGND=inputStream.readInt();
//#             instance.CURSOR_OUTLINE=inputStream.readInt();
//#if NICK_COLORS
//#             instance.MESSAGE_IN_S=inputStream.readInt();
//#             instance.MESSAGE_OUT_S=inputStream.readInt();
//#             instance.MESSAGE_PRESENCE_S=inputStream.readInt();
//#else
//#             inputStream.readInt();
//#             inputStream.readInt();
//#             inputStream.readInt();
//#endif
//#             instance.CONTACT_J2J=inputStream.readInt();
//#             instance.BAR_BGND_BOTTOM=inputStream.readInt();
//# 
//# 	    inputStream.close();
//# 	} catch (Exception e) { }
//#     }
//# 
//#     public static void saveToStorage(){
//# 	DataOutputStream outputStream=NvStorage.CreateDataOutputStream();
//# 	try {
//# 	    outputStream.writeInt(instance.BALLOON_INK);
//# 	    outputStream.writeInt(instance.BALLOON_BGND);
//# 	    outputStream.writeInt(instance.LIST_BGND);
//# 	    outputStream.writeInt(instance.LIST_BGND_EVEN);
//# 	    outputStream.writeInt(instance.LIST_INK);
//# 	    outputStream.writeInt(instance.MSG_SUBJ);
//# 	    outputStream.writeInt(instance.MSG_HIGHLIGHT);
//# 	    outputStream.writeInt(instance.DISCO_CMD);
//# 	    outputStream.writeInt(instance.BAR_BGND);
//# 	    outputStream.writeInt(instance.BAR_INK);
//# 	    outputStream.writeInt(instance.CONTACT_DEFAULT);
//# 	    outputStream.writeInt(instance.CONTACT_CHAT);
//# 	    outputStream.writeInt(instance.CONTACT_AWAY);
//# 	    outputStream.writeInt(instance.CONTACT_XA);
//# 	    outputStream.writeInt(instance.CONTACT_DND);
//# 	    outputStream.writeInt(instance.GROUP_INK);
//# 	    outputStream.writeInt(instance.BLK_INK);
//# 	    outputStream.writeInt(instance.BLK_BGND);
//# 	    outputStream.writeInt(instance.MESSAGE_IN);
//# 	    outputStream.writeInt(instance.MESSAGE_OUT);
//# 	    outputStream.writeInt(instance.MESSAGE_PRESENCE);
//# 	    outputStream.writeInt(instance.MESSAGE_AUTH);
//# 	    outputStream.writeInt(instance.MESSAGE_HISTORY);
//# 	    outputStream.writeInt(instance.PGS_REMAINED);
//# 	    outputStream.writeInt(instance.PGS_COMPLETE);
//# 	    outputStream.writeInt(0);//outputStream.writeInt(PGS_BORDER);
//# 	    outputStream.writeInt(0);//outputStream.writeInt(PGS_BGND);
//# 	    outputStream.writeInt(instance.HEAP_TOTAL);
//# 	    outputStream.writeInt(instance.HEAP_FREE);
//# 	    outputStream.writeInt(instance.CURSOR_BGND);
//# 	    outputStream.writeInt(instance.SCROLL_BRD);
//# 	    outputStream.writeInt(instance.SCROLL_BAR);
//# 	    outputStream.writeInt(instance.SCROLL_BGND);
//# 	    outputStream.writeInt(instance.CURSOR_OUTLINE);
//#if NICK_COLORS
//# 	    outputStream.writeInt(instance.MESSAGE_IN_S);
//# 	    outputStream.writeInt(instance.MESSAGE_OUT_S);
//# 	    outputStream.writeInt(instance.MESSAGE_PRESENCE_S);
//#else
//# 	    outputStream.writeInt(0);
//# 	    outputStream.writeInt(0);
//# 	    outputStream.writeInt(0);
//#endif
//# 	    outputStream.writeInt(instance.CONTACT_J2J);
//# 	    outputStream.writeInt(instance.BAR_BGND_BOTTOM);
//# 
//#         } catch (IOException e) { }
//# 	NvStorage.writeFileRecord(outputStream, "ColorDB", 0, true);
//#     }
//#endif
}
