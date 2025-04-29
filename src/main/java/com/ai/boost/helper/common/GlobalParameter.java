package com.ai.boost.helper.common;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public final class GlobalParameter {
    public final static String COLLECTION_NAME = "Test_Collection";

    public final static String[] CAR_MAKER = new String[] {"Toyota","Volkswagen","Ford","GeneralMotors(Chevrolet","GMC","Cadillac","Buick)","Honda","Nissan","BMW","Mercedes-Benz","Audi","Hyundai","Kia","Tesla","Stellantis","Peugeot","Fiat","Chrysler","Citroën","Opel","Jeep","Dodge","AlfaRomeo","Subaru","Mazda","Mitsubishi","Renault","Volvo","Porsche","Lexus","MINI","LandRover","Jaguar","Ferrari","Lamborghini","Bentley","Rolls-Royce","Bugatti","Maserati","Genesis","Polestar","Koenigsegg","BYD","NIO","XPeng","Geely","Chery","SAIC"};

    public final static Map<String, List<String>> CAR_MODEL = new FluentMap<String, List<String>>().chainPut("Toyota", Arrays.asList("Camry", "Corolla", "RAV4", "Prius", "Tacoma", "Highlander", "4Runner", "Tundra", "Sienna", "Avalon"))
            .chainPut("Toyota", Arrays.asList("Camry", "Corolla", "RAV4", "Prius", "Tacoma", "Highlander", "4Runner", "Tundra", "Sienna", "Avalon"))
            .chainPut("Honda", Arrays.asList("Accord", "Civic", "CR-V", "Pilot", "Odyssey", "Fit", "HR-V", "Ridgeline"))
            .chainPut("Ford", Arrays.asList("F-150", "Mustang", "Explorer", "Escape", "Edge", "Fusion", "Ranger", "Bronco"))
            .chainPut("Chevrolet", Arrays.asList("Silverado", "Malibu", "Equinox", "Traverse", "Camaro", "Tahoe", "Suburban"))
            .chainPut("Nissan", Arrays.asList("Altima", "Sentra", "Rogue", "Pathfinder", "Frontier", "Maxima", "Murano"))
            .chainPut("Hyundai", Arrays.asList("Elantra", "Sonata", "Tucson", "Santa Fe", "Kona", "Palisade"))
            .chainPut("Kia", Arrays.asList("Sorento", "Sportage", "Optima", "Soul", "Telluride", "Forte"))
            .chainPut("Volkswagen", Arrays.asList("Golf", "Jetta", "Passat", "Tiguan", "Atlas", "Beetle"))
            .chainPut("BMW", Arrays.asList("3 Series", "5 Series", "7 Series", "X3", "X5", "X7", "Z4", "i3", "i8"))
            .chainPut("Mercedes-Benz", Arrays.asList("C-Class", "E-Class", "S-Class", "GLC", "GLE", "GLS", "A-Class", "CLA"))
            .chainPut("Audi", Arrays.asList("A3", "A4", "A6", "A8", "Q3", "Q5", "Q7", "Q8", "TT", "R8"))
            .chainPut("Subaru", Arrays.asList("Impreza", "Legacy", "Outback", "Forester", "Crosstrek", "Ascent"))
            .chainPut("Mazda", Arrays.asList("Mazda3", "Mazda6", "CX-3", "CX-5", "CX-9", "MX-5 Miata"))
            .chainPut("Lexus", Arrays.asList("IS", "ES", "GS", "LS", "NX", "RX", "GX", "LX"))
            .chainPut("Acura", Arrays.asList("ILX", "TLX", "RDX", "MDX", "NSX"))
            .chainPut("Tesla", Arrays.asList("Model S", "Model 3", "Model X", "Model Y", "Cybertruck"))
            .chainPut("Porsche", Arrays.asList("911", "Cayenne", "Macan", "Panamera", "Taycan"))
            .chainPut("Jeep", Arrays.asList("Wrangler", "Cherokee", "Grand Cherokee", "Compass", "Renegade", "Gladiator"))
            .chainPut("Ram", Arrays.asList("1500", "2500", "3500", "ProMaster"))
            .chainPut("GMC", Arrays.asList("Sierra", "Yukon", "Acadia", "Terrain", "Canyon"))
            .chainPut("Dodge", Arrays.asList("Charger", "Challenger", "Durango", "Journey"))
            .chainPut("Cadillac", Arrays.asList("CT4", "CT5", "XT4", "XT5", "XT6", "Escalade"))
            .chainPut("Buick", Arrays.asList("Encore", "Enclave", "Envision", "Regal", "LaCrosse"))
            .chainPut("Infiniti", Arrays.asList("Q50", "Q60", "QX50", "QX60", "QX80"))
            .chainPut("Genesis", Arrays.asList("G70", "G80", "G90", "GV70", "GV80"))
            .chainPut("Land Rover", Arrays.asList("Range Rover", "Discovery", "Defender", "Evoque"))
            .chainPut("Jaguar", Arrays.asList("XE", "XF", "XJ", "F-PACE", "E-PACE", "F-TYPE"))
            .chainPut("Volvo", Arrays.asList("S60", "S90", "V60", "V90", "XC40", "XC60", "XC90"))
            .chainPut("Mitsubishi", Arrays.asList("Outlander", "Eclipse Cross", "Mirage", "Lancer"))
            .chainPut("Mini", Arrays.asList("Cooper", "Clubman", "Countryman", "Paceman"))
            .chainPut("Alfa Romeo", Arrays.asList("Giulia", "Stelvio", "4C"))
            .chainPut("Fiat", Arrays.asList("500", "500X", "500L", "Panda"))
            .chainPut("Peugeot", Arrays.asList("208", "308", "508", "2008", "3008", "5008"))
            .chainPut("Renault", Arrays.asList("Clio", "Megane", "Captur", "Kadjar", "Koleos"))
            .chainPut("Citroën", Arrays.asList("C3", "C4", "C5", "C5 Aircross"))
            .chainPut("Skoda", Arrays.asList("Octavia", "Superb", "Fabia", "Kodiaq", "Karoq"))
            .chainPut("SEAT", Arrays.asList("Ibiza", "Leon", "Arona", "Ateca", "Tarraco"))
            .chainPut("Suzuki", Arrays.asList("Swift", "Vitara", "Jimny", "Baleno"))
            .chainPut("Isuzu", Arrays.asList("D-Max", "MU-X"))
            .chainPut("Mahindra", Arrays.asList("Scorpio", "XUV500", "Thar", "Bolero"))
            .chainPut("Tata", Arrays.asList("Nexon", "Harrier", "Altroz", "Tiago"))
            .chainPut("BYD", Arrays.asList("Han", "Tang", "Qin", "Song", "Yuan"))
            .chainPut("Geely", Arrays.asList("Emgrand", "Boyue", "Binrui", "Jiaji"))
            .chainPut("Chery", Arrays.asList("Tiggo", "Arrizo", "QQ"))
            .chainPut("NIO", Arrays.asList("ES6", "ES8", "EC6", "ET7"))
            .chainPut("XPeng", Arrays.asList("P7", "G3", "G9"))
            .chainPut("Lucid Motors", Arrays.asList("Air", "Gravity"))
            .chainPut("Rivian", Arrays.asList("R1T", "R1S"))
            .chainPut("Fisker", Arrays.asList("Ocean", "Pear"))
            .chainPut("Koenigsegg", Arrays.asList("Jesko", "Gemera", "Regera"))
            .chainPut("Pagani", Arrays.asList("Huayra", "Zonda"))
            .chainPut("Bugatti", Arrays.asList("Chiron", "Veyron", "Divo"))
            .chainPut("Lamborghini", Arrays.asList("Aventador", "Huracán", "Urus"))
            .chainPut("Ferrari", Arrays.asList("488", "F8 Tributo", "Roma", "Portofino", "SF90"))
            .chainPut("McLaren", Arrays.asList("540C", "570S", "720S", "Artura", "P1"))
            .chainPut("Rolls-Royce", Arrays.asList("Phantom", "Ghost", "Wraith", "Cullinan"))
            .chainPut("Bentley", Arrays.asList("Continental GT", "Flying Spur", "Bentayga"))
            .chainPut("Aston Martin", Arrays.asList("Vantage", "DB11", "DBS", "DBX"));

    public final static Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    public static final String CAR_ID_FIELD = "car_id";
    public static final String SPACE = " ";
    public static final String EQUAL = "==";
}
