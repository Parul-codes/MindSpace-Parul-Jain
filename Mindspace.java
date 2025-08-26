import java.util.Scanner;
import detox.Detox;
import music.MusicManager;
import logger.logger;

public class Mindspace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Detox d = new Detox();
        MusicManager m = new MusicManager();
        logger l = new logger();
        int c;
        do {
            System.out.println("\nWelcome to MindSpace - Your Personal Mood Keeper");
            System.out.println("1. Digital Detox - Mood logger");
            System.out.println("2. Diary Entry");
            System.out.println("3. Mood based Music Recs");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            c = sc.nextInt();
            sc.nextLine();
            switch (c) {
                case 1: int choice;
                    do {
                        System.out.println("\nWelcome to Digital Detox - Mood Logger");
                        System.out.println("1. Start focus timer");
                        System.out.println("2. View focus history");
                        System.out.println("3. Exit");
                        System.out.print("Choose an option (1-3) : ");
                        choice = sc.nextInt();
                        sc.nextLine();

                        switch (choice) {
                            case 1 -> {
                                System.out.print("How many minutes do you want to focus? (1 - 1440) : ");
                                int time = sc.nextInt();
                                sc.nextLine();
                                d.startFocusTimer(time, sc);
                                d.motivationalQuotes();
                                d.updateStreak();
                                d.showStreak();
                            }
                            case 2 -> d.viewHistory();
                            case 3 -> System.out.println("Stay mindful, see you soon!");
                            default -> System.out.println("Invalid choice. Try again.");
                        }
                    } while (choice != 3);
                    
                    break;
                case 2: 
                int ch;
                do {
                    System.out.println("Welcome to Diary Entry!");
                    System.out.println("1. Log Entry");
                    System.out.println("2. View Past Entries");
                    System.out.println("3. Exit");
                    System.out.print("Choose an option (1-3)");
                    ch = sc.nextInt();
                    sc.nextLine();

                    switch (ch) {
                        case 1-> {
                            System.out.print("Log your entry :");
                            String journal = sc.nextLine();
                            l.write(journal);
                        }
                        case 2 -> l.viewlog();
                        case 3 -> System.out.println("Stay Positive! See you Soon");
                        default -> System.out.println("Invalid Choice. Try again!");
                    }
                } while(ch!=3); 

                break;

                case 3: m.loadDefaultTracks();
                m.readTracks();
                int cho;
                do {
                    System.out.println("Welcome to Mood-Based Music Recommender!");
                    System.out.println("How are you feeling today?");
                    System.out.println("1. Happy");
                    System.out.println("2. Sad");
                    System.out.println("3. Focused");
                    System.out.println("4. Energetic");
                    System.out.println("5. Chill");
                    System.out.println("6. Add a new song");
                    System.out.println("7. Surprise Me! (Random Mood)");
                    System.out.println("8. Exit");
                    System.out.print("Enter your choice (1-8) : ");
                    cho = sc.nextInt();
                    sc.nextLine();

                    switch(cho) {
                        case 1 :
                            m.getTracksByMood("Happy");
                            break;
                        case 2:
                            m.getTracksByMood("Sad");  
                            break;
                        case 3:
                            m.getTracksByMood("Focused"); 
                            break;
                        case 4:
                            m.getTracksByMood("Energetic");
                            break;
                        case 5:
                            m.getTracksByMood("Chill");
                            break;
                        case 6:
                            System.out.print("Write title : ");
                            String title = sc.nextLine();
                            System.out.print("Write artist : ");
                            String artist = sc.nextLine();
                            System.out.print("Write mood : ");
                            String mood = sc.nextLine();
                            System.out.print("Write energy level (1-5): ");
                            int energy = sc.nextInt();
                            sc.nextLine();
                            m.addNewTrack(title, artist, mood, energy);
                            break;
                        case 7:
                            m.getTracksByMood(m.random());
                            break;
                        case 8:
                            m.saveTracks();
                            System.out.println("Exiting Mood-Based Music Recommender. Goodbye!");
                            break;
                        default :
                            System.out.println("❌ Invalid choice. Please try again.");
                            break;
                        }
                } while(cho != 8);
                break; 

                case 4: System.out.println("There is no love stronger than self love! See you soon");
                break;
                default: System.out.println("Invalid choice. Try again!");
                    break;
            }
        }while(c !=4);
        
        sc.close();
    }
}
