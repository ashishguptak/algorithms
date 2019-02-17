/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.factorymethod;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public enum WeaponType {

    SHORTSWORD("short_sword"),
    SPEAR("spear"),
    AXE("axe"),
    UNDEFINED("");

    private String title;

    WeaponType(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
