package music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MusicManager {
    public class Track {
        String title, artist, mood;
        int energyLevel;

        Track(String title, String artist, String mood, int energyLevel) {
            this.title = title;
            this.artist = artist;
            this.mood = mood;
            this.energyLevel = energyLevel;
        }

        public void displayInfo() {
            System.out.println(title+ " - "+artist+"("+mood+" | "+energyLevel+" )");
        }
    }

    public class Playlist{
        String moodName;
        ArrayList<Track> tracks = new ArrayList<>();

        Playlist(String moodName) {
            this.moodName = moodName;
        }

        public void addTrack(Track track) {
            tracks.add(track);
            System.out.println("Track added succesfully");
        }

        public void displayTracks() {
            if(tracks.isEmpty()) {
                System.out.println("No tracks are present");
                return;
            }
            for(Track track : tracks) {
                track.displayInfo();
            }
        }
    }

    ArrayList<Track> allTracks = new ArrayList<>();
    public void loadDefaultTracks() {
        allTracks.add(new Track("Happy","Pharrell Williams","Happy",4));
        allTracks.add(new Track("Someone Like You ","Adele","Sad",1));
        allTracks.add(new Track("Weightless","Marconi Union","Focused",3));
        allTracks.add(new Track("Believer","Imagine Dragons","Energetic",5));
        allTracks.add(new Track("Perfect","Ed Sheeran","Chill",2));
    }

    public void getTracksByMood(String mood) {
        boolean found = false;
        System.out.println("Here are some songs for your mood : ");
        for(Track track : allTracks) {
            if(track.mood.equalsIgnoreCase(mood)) {
                track.displayInfo();
                found = true;
            }
        }
        if(!found) {
            System.out.println("No songs matching this mood");
        }
    }

    public void addNewTrack(String title, String artist, String mood, int energyLevel) {
        Track newTrack = new Track(title, artist, mood, energyLevel);
        allTracks.add(newTrack);
        System.out.println("New song added successfully!");
    }

    public String random() {
        ArrayList<String> moods = new ArrayList<>();
        moods.add("Happy");
        moods.add("Sad");
        moods.add("Focused");
        moods.add("Energetic");
        moods.add("Chill");
        Random ran = new Random();
        int ranInd = ran.nextInt(moods.size());
        String ranMood = moods.get(ranInd);
        return ranMood;
    }

    public void saveTracks() {
        try {
            FileWriter writer = new FileWriter("Tracks.txt");
            for(Track track : allTracks) {
                writer.write(track.title+","+track.artist+","+track.mood+","+track.energyLevel+"\n");
            }
            writer.close();
            System.out.println("Tracks saved to file successfully!");
        } catch(IOException e ) {
            System.out.println("Error saving tracks in file "+e.getMessage());
        }
    }

    public void readTracks() {
        try {
            BufferedReader read = new BufferedReader(new FileReader("Tracks.txt"));
            String line;
            while((line = read.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                String artist = parts[1];
                String mood = parts[2];
                int energy = Integer.parseInt(parts[3]);
                Track track = new Track(title, artist, mood, energy);
                allTracks.add(track);
            }
            read.close();
            System.out.println("Tracks loaded successfully");
        } catch (IOException e) {
            System.out.println("Error in reading "+e.getMessage());
        }
    }
}
