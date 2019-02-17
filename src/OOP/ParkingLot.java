/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package OOP;

/**
 * Approach -
 *
 * Initialise Parking Lot based on Multi-Level Multi-Spot input
 * Parking Spot for people with disability
 * Talk about price for Spots/ Vehicle
 * Write all methods exposed by ParkingLot class
 * How to handle Spots when spotsize for VehicleType is not available but higher ones
 * How to identify an Vehicle quickly when parked
 *
 * Can extend to Database design of Single App for many Parking Lots
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ParkingLot {

    public Spot placeVehicle(){return new Spot();}

    public Spot findVehicle(){
        return null;
    }

    public class Spot{}
}


