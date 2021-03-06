/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//#ifdef FILE_TRANSFER

package io.file.transfer;

import io.file.browse.Browser;
import io.file.browse.BrowserListener;
import locale.SR;
import ui.VirtualList;
import ui.controls.form.DefForm;
import ui.controls.form.LinkString;
import ui.controls.form.TextInput;


/**
 *
 * @author Vitaly
 */
public class TransferConfigForm extends DefForm implements BrowserListener {
    
    private TextInput streamhost;
    private TextInput transferFolder;
    private LinkString selectFolder;
    
    private TransferConfig ft = TransferConfig.getInstance();

    public TransferConfigForm(VirtualList parentView) {
        super ("File transfer");        
        transferFolder = new TextInput("Save files to", ft.ftFolder, null);
        itemsList.addElement(transferFolder);
        selectFolder=new LinkString(SR.MS_SELECT) { public void doAction() { selectFolder(); } };
        itemsList.addElement(selectFolder);
        streamhost = new TextInput("SOCKS5 proxy", ft.ftProxy, "ft_proxyjid", 0);
        itemsList.addElement(streamhost);
        
        
    }

    public void cmdOk() {
        ft.ftFolder = transferFolder.getValue();
        ft.ftProxy = streamhost.getValue();
        ft.saveToStorage();
        destroyView();
    }
    
     public void selectFolder() {
        new Browser(null, this, true);
    }

    public void BrowserFilePathNotify(String pathSelected) {
        transferFolder.setValue(pathSelected);
    }
    
    

}

//#endif
