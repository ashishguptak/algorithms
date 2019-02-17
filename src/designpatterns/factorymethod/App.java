/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.factorymethod;

import java.util.logging.Logger;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class App {

    private static final Logger LOGGER = Logger.getGlobal();

    private final BlackSmith blackSmith;

    public App(BlackSmith blackSmith){
        this.blackSmith = blackSmith;
    }

    public static void main(String[] args){
        App app = new App(new OrcsBlackSmith());
        app.manfWeapons();

        app = new App(new ElfSmith());
        app.manfWeapons();
    }

    private void manfWeapons() {
        Weapon weapon;
        weapon = blackSmith.manfWeapon(WeaponType.AXE);
        LOGGER.info(weapon.toString());
        weapon = blackSmith.manfWeapon(WeaponType.SPEAR);
        LOGGER.info(weapon.toString());
    }

}
