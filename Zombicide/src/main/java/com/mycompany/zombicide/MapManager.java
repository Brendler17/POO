package com.mycompany.zombicide;

import java.io.*;
import java.util.*;

public class MapManager {

    private static final String MAPS_FOLDER = "/maps/";
    private static final String[] MAPS_FILES = {"map_1.txt", "map_2.txt", "map_3.txt", "map_4.txt"};

    public static char[][] loadRandomMap() {
        Random random = new Random();
        String selectedMap = MAPS_FILES[random.nextInt(MAPS_FILES.length)];
        return loadMap(selectedMap);
    }

    private static char[][] loadMap(String mapName) {
        List<String> lines = new ArrayList<>();

        try (InputStream inputStream = MapManager.class.getResourceAsStream(MAPS_FOLDER + mapName); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o mapa " + mapName);
            return new char[0][0];
        }

        int rows = lines.size();
        int columns = lines.get(0).split(" ").length;
        char[][] mapMatrix = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            String[] elements = lines.get(i).split(" ");
            for (int j = 0; j < columns; j++) {
                mapMatrix[i][j] = elements[j].charAt(0);
            }
        }

        return mapMatrix;
    }
}
