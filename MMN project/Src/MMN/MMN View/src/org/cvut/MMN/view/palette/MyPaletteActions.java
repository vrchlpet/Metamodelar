

package org.cvut.MMN.view.palette;

import javax.swing.Action;
import org.netbeans.spi.palette.PaletteActions;
import org.openide.util.Lookup;

/**
 *
 * @author Vrchli
 */
public class MyPaletteActions extends PaletteActions{

    @Override
    public Action[] getImportActions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action[] getCustomPaletteActions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action[] getCustomCategoryActions(Lookup lkp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action[] getCustomItemActions(Lookup lkp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action getPreferredAction(Lookup lkp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
