/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.factorymethod;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class OrcsWeapon implements Weapon {
    private WeaponType weaponType;

    public OrcsWeapon(WeaponType weaponType){
        this.weaponType = weaponType;
    }

    @Override
    public String toString(){
        return "Orcs"+weaponType;
    }

    @Override
    public WeaponType getWeaponType() {
        return weaponType;
    }
}
