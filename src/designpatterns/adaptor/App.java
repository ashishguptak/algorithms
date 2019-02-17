/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.adaptor;

import java.util.logging.Logger;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class App {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args){
        Captain captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
