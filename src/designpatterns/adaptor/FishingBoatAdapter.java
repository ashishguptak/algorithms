/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.adaptor;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class FishingBoatAdapter implements RowingBoat {
    private FishingBoat fishingBoat;

    public FishingBoatAdapter( ){
        this.fishingBoat = new FishingBoat();
    }

    @Override
    public void row() {
        fishingBoat.sail();
    }
}
