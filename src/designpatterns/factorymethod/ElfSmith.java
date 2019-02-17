/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.factorymethod;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class ElfSmith implements BlackSmith {
    @Override
    public Weapon manfWeapon(WeaponType weaponType) {
        return new ElfWeapon(weaponType);
    }
}
