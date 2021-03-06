/*
 * RegForm.java
 *
 * Created on 5.06.2005, 20:04
 * Copyright (c) 2005-2008, Eugene Stahov (evgs), http://bombus-im.org
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

package ServiceDiscovery;
import Menu.MenuCommand;
import java.util.*;
import com.alsutton.jabber.*;
import com.alsutton.jabber.datablocks.*;
import javax.microedition.lcdui.Image;
import locale.SR;
import ui.VirtualList;
import ui.controls.form.ComplexForm;
import ui.controls.form.ImageItem;
import ui.controls.form.SimpleString;
import util.Strconv;
//import Client.*;


/**
 *
 * @author Evg_S
 */
public class DiscoForm extends ComplexForm{
    
    private Vector fields;
    private String xmlns;
    private String service;
    
    private String node;
    private String sessionId;
    
    private String childName;
    
    //private Form form;
    
    private boolean xData;
    
    private String id;

    private boolean complete = false;
    
    //Roster roster=StaticData.getInstance().roster;
    JabberStream stream;

    public final static String NS_XDATA = "jabber:x:data";

    ServiceDiscovery disco;
    FormSubmitListener listener;
    MenuCommand cmdSend = new MenuCommand(SR.MS_SEND, MenuCommand.OK, 1);
    
    //private JabberBlockListener listener;
    
    /** Creates a new instance of RegForm
     * @param regform 
     * @param resultId
     * @param stream
     * @param childName
     */
    public DiscoForm(ServiceDiscovery disco, FormSubmitListener listener, String to, JabberDataBlock regform, JabberStream stream, String resultId, String childName) {
        super(regform.getAttribute("from"), false);
        service = (to == null) ? regform.getAttribute("from") : to;
        this.disco = disco;
        this.listener = listener;
        JabberDataBlock query = (childName == null) ? regform : regform.getChildBlock(childName);
        this.childName = query.getTagName();
        xmlns = query.getAttribute("xmlns");
        node = query.getAttribute("node");
        sessionId = query.getAttribute("sessionid");
        JabberDataBlock x = query.findNamespace("x", NS_XDATA);
        this.id = resultId;
        //this.listener=listener;
        // todo: обработать ошибку query
        fields = new Vector();
        
        // for instructions
        
        Vector vFields=(xData=(x!=null))? x.getChildBlocks() : query.getChildBlocks();

	Enumeration e;        
        
        if (vFields!=null) {
            for (e=vFields.elements(); e.hasMoreElements(); ){
                FormField field=new FormField((JabberDataBlock)e.nextElement());
                fields.addElement(field); 
            }           
            
            for (e=fields.elements(); e.hasMoreElements(); ){
                FormField field=(FormField) e.nextElement();
                if (!field.hidden || field.registered) 
                    itemsList.addElement(field.formItem);
            }
        }
        
       
        if (this.childName.equals("command")) {
            if (query.getAttribute("status").equals("completed")) {
                itemsList.addElement(new SimpleString("Complete.", false));
                complete = true;
            } 
        }
        this.stream = stream;
	//moveCursorTo(getNextSelectableRef(-1));
	show();	
    }
    
    private void sendForm(String id){
        JabberDataBlock req=new Iq(service, Iq.TYPE_SET, id);
        JabberDataBlock qry=req.addChildNs(childName, xmlns);
        //qry.setAttribute("action", "complete");
        qry.setAttribute("node", node);
        qry.setAttribute("sessionid", sessionId);	
        
        JabberDataBlock cform=qry;
        if (xData) {            
            JabberDataBlock x=qry.addChildNs("x", DiscoForm.NS_XDATA);
            x.setAttribute("type", "submit");
            cform=x;
        }
        if (listener != null)
	        listener.formSubmit(fields);
        for (Enumeration e=fields.elements(); e.hasMoreElements(); ) {
            FormField f=(FormField) e.nextElement();
            if (f==null) continue;
            JabberDataBlock ch=f.constructJabberDataBlock();
            if (ch!=null) {
                if (ch.getTagName().equals("remove")) {
                    cform=qry;
                    Vector cb=cform.getChildBlocks();
                    if (cb!=null) cb.removeAllElements();
                    cform.addChild(ch);
                    break;
                }
                cform.addChild(ch);
            }
        }
	stream.send(req);	
    }

    
    public void destroyView() {
	parentView = sd.roster;
	super.destroyView();
    }

     public void fetchMediaElements(Vector bobCache) {
        //TODO: fetch external http bobs and non-cached in-band bobs
        byte [] bytes = null;
        Image img = null;
        String cid = null;
        FormField field = null;
        JabberDataBlock data = null;
        int formItems = fields.size();
        for (int i=0; i<formItems; i++) {
            field=(FormField)fields.elementAt(i);
            if (field.mediaUri==null) continue;
            if (!(field.media instanceof ImageItem)) continue;

            if (field.mediaUri.startsWith("cid:")) {
                cid = field.mediaUri.substring(4);
                if (bobCache==null) continue; //TODO: in-band bob request

                for (int bob=0; bob<bobCache.size(); bob++) {
                    data = (JabberDataBlock) bobCache.elementAt(bob);
                    if (data.isJabberNameSpace("urn:xmpp:bob") && cid.equals(data.getAttribute("cid"))) {
                        bytes = Strconv.fromBase64(data.getText());
                        img = Image.createImage(bytes, 0, bytes.length);
                        if (field.media != null) {
                            ((ImageItem)field.media).img = img;
                            itemsList.addElement(field.media);
                        }
                    }
                }
            }
        }
    }

     // TODO: fix this shit
    public void commandState() {
        menuCommands.removeAllElements();
	if (!complete) {
	    addMenuCommand(cmdSend);
	}
    }
    public void menuAction(MenuCommand c, VirtualList v) {
        if (c == cmdSend) {
            if (!complete) {
                sendForm(id);
                destroyView();
            }
        } else {
            super.menuAction(c, v);
        }
    }

    public interface FormSubmitListener {
	    public void formSubmit(Vector fields);
    }
}
