/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package designpatterns.factorymethod;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class ElfWeapon implements Weapon {
    private WeaponType weaponType;

    public ElfWeapon(WeaponType weaponType){
        this.weaponType = weaponType;
    }

    @Override
    public String toString(){
        return "Elf"+weaponType;
    }

    @Override
    public WeaponType getWeaponType() {
        return weaponType;
    }
}
