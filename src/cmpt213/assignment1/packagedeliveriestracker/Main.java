package cmpt213.assignment1.packagedeliveriestracker;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    /**
     * Load the json file from the project folder and convert it to an ArrayList. If the file does not exist, create a new json file.
     * @return An ArrayList of packages
     * @throws IOException
     */
    public static ArrayList<Package> loadJsonFile() throws IOException {
        File inputFile = new File("./list.json");
        if (!inputFile.exists()){
            inputFile.createNewFile();
        }
        //These lines of code are provided by Prof. Victor Cheung in assignment description
        Gson myGson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                        jsonWriter.value(localDateTime.toString());
                    }
                    @Override
                    public LocalDateTime read(JsonReader jsonReader) throws IOException {
                        return LocalDateTime.parse(jsonReader.nextString());
                    }
                }).create();
        //-------------------------------------------------------------------------------------
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Type listType = new TypeToken<ArrayList<Package>>(){}.getType();
        ArrayList<Package> packageList = myGson.fromJson(reader, listType);

        return packageList;

    }

    /**
     * Convert the list of packages to json format and save it to a json file.
     * @throws IOException
     */
    public static void saveJsonFile() throws IOException {
        PackageList packageList = PackageList.getInstance();
        List<Package> packages = packageList.getPackages();
        //These lines of code are provided by Prof. Victor Cheung in assignment description
        Gson myGson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
            @Override
            public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                jsonWriter.value(localDateTime.toString());
            }
            @Override
            public LocalDateTime read(JsonReader jsonReader) throws IOException {
                return LocalDateTime.parse(jsonReader.nextString());
            }
        }).create();
        //------------------------------------------------------------------------------
        Type listType = new TypeToken<ArrayList<Package>>(){}.getType();
        JsonElement element = myGson.toJsonTree(packages, listType);
        String jsonString = element.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./list.json"));
        writer.write(jsonString);
        writer.close();
    }


    /**
     * Main method of the project
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    ArrayList<Package> packageArrayList = loadJsonFile();
    PackageList packageList = PackageList.getInstance();
    if (packageArrayList!=null) {
        for (int i = 0; i < packageArrayList.size(); i++) {
            packageList.addPackage(packageArrayList.get(i));
        }
    }
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    boolean continueLoop = true;
    while (continueLoop) {
        menu.printMenu();
        int input = menu.getInputOption();
        switch (input) {
            case 1: {
                packageList.listAllPackages();
                break;
            }
            case 2: {
                Package newPackage = menu.getInputForNewPackage();
                packageList.addPackage(newPackage);
                break;
            }
            case 3: {
                packageList.listAllPackages();
                System.out.print("Enter the item number you want to remove (0 to cancel)");
                int removeInput = scanner.nextInt();
                if (removeInput == 0) {
                    break;
                } else {
                    packageList.removePackage(removeInput - 1);
                }
                break;
            }
            case 4: {
                packageList.listOverduePackages();
                break;
            }
            case 5: {
                packageList.listUpcomingPackages();
                break;
            }
            case 6: {
                packageList.listAllPackages();
                System.out.print("Enter the item number you want to mark (0 to cancel)");
                int markingInput = scanner.nextInt();
                if (markingInput == 0) {
                    break;
                } else {
                    packageList.markDelivered(markingInput - 1);
                }
                break;
            }
            case 7: {
                continueLoop = false;
                saveJsonFile();
                break;
            }
        }
    }



    }
}

