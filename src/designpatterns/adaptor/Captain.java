/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.adaptor;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class Captain implements RowingBoat{
    private RowingBoat rowingBoat;

    public Captain(){}

    public Captain(RowingBoat rowingBoat){
        this.rowingBoat = rowingBoat;
    }

    @Override
    public void row() {
        rowingBoat.row();
    }
}
