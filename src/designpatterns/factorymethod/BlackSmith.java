/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.factorymethod;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public interface BlackSmith {
    Weapon manfWeapon(WeaponType weaponType);
}
