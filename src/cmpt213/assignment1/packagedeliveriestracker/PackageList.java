package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class manages the list of packages using singleton pattern
 * @author Nhat Huy Phan (James)
 */
public class PackageList {
    private List<Package> packages = new ArrayList<Package>();

    private static PackageList instance;
    private PackageList(){

    }

    /**
     * Get the list of packages
     * @return the list of packages
     */
    public List<Package> getPackages() {
        return packages;
    }

    /**
     * Get the static instance of PackageList class
     * @return the static instance of PackageList class
     */
    public static PackageList getInstance(){
        if (instance == null){
            instance = new PackageList();
        }
        return instance;
    }

    /**
     * Print all the packages in the list
     */
    public void listAllPackages(){
        if( packages.isEmpty()){
            System.out.println("No packages to show");
            return;
        }
        System.out.println("");
        for(int index=0; index < packages.size(); index ++){
            System.out.println("Packages #" + (index+1));
            System.out.println(packages.get(index));
            System.out.println("");
        }
    }

    /**
     * Add a new package to the list
     * @param newPackage A new package to be added to the list
     */
    public void addPackage(Package newPackage){
        packages.add(newPackage);
        System.out.println(newPackage.getName() + " has been added to the list");
    }

    /**
     * Remove a package from the package list
     * @param packageIndex The index of package in the list
     */
    public void removePackage(int packageIndex){
        packages.remove(packageIndex);
    }

    /**
     * Mark a package as delivered
     * @param packageIndex The index of package in the list
     */
    public void markDelivered(int packageIndex){
        Package markingPackage = packages.get(packageIndex);
        markingPackage.setDelivered(true);
        System.out.println(markingPackage.getName() + " has been delivered" );
    }

    /**
     * Print all the overdue packages
     */
    public void listOverduePackages(){
        int overdueCounter = 0;
        LocalDateTime today = LocalDateTime.now();
        List<Package> tempList= new ArrayList<>();
        for (int i = 0; i < packages.size(); i++){
            Package item = packages.get(i);
            if (today.isAfter(item.getExpectedDeliveryDate()) && !item.isDelivered()){
                tempList.add(item);           }
        }
        Collections.sort(tempList);
        for (int i = 0; i < tempList.size(); i++){
            Package item = tempList.get(i);
            if (today.isAfter(item.getExpectedDeliveryDate()) && !item.isDelivered()){
                System.out.println("");
                overdueCounter++;
                System.out.println("Packages #" + (overdueCounter));
                System.out.println(item);
            }
        }
        if(overdueCounter == 0){
            System.out.println("No overdue packages to show");
        }
    }

    /**
     * Print all the upcoming packages
     */
    public void listUpcomingPackages(){
        int upcomingCounter = 0;
        LocalDateTime today = LocalDateTime.now();
        List<Package> tempList= new ArrayList<>();
        for (int i = 0; i < packages.size(); i++){
            Package item = packages.get(i);
            if (today.isBefore(item.getExpectedDeliveryDate()) && !item.isDelivered()){
                tempList.add(item);           }
        }
        Collections.sort(tempList);
        for (int i = 0; i < tempList.size(); i++){
            Package item = tempList.get(i);
            if (today.isBefore(item.getExpectedDeliveryDate()) && !item.isDelivered()){
                System.out.println("");
                upcomingCounter++;
                System.out.println("Packages #" + (upcomingCounter));
                System.out.println(item);
            }
        }
        if(upcomingCounter == 0){
            System.out.println("No upcoming packages to show");
        }
    }
}
