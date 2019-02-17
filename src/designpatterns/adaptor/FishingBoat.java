/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.adaptor;

import java.util.logging.Logger;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class FishingBoat {
    private static final Logger LOGGER = Logger.getGlobal();

    public void sail(){
        LOGGER.info("the fishing boat is sailing");
    }
}
