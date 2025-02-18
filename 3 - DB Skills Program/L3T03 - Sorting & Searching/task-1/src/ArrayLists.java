import java.util.ArrayList;
import java.util.Collections;

public class ArrayLists {
  public static class Album {
    private String albumName;
    private int numberOfSongs;
    private String albumArtist;

    // Constructor
    public Album(String albumName, int numberOfSongs, String albumArtist) {
      this.albumName = albumName;
      this.numberOfSongs = numberOfSongs;
      this.albumArtist = albumArtist;
    }

    // Getters
    public String getAlbumName() {
      return albumName;
    }

    public int getNumberOfSongs() {
      return numberOfSongs;
    }

    public String getAlbumArtist() {
      return albumArtist;
    }

    // Setters
    public void setAlbumName(String albumName) {
      this.albumName = albumName;
    }

    public void setNumberOfSongs(int numberOfSongs) {
      this.numberOfSongs = numberOfSongs;
    }

    public void setAlbumArtist(String albumArtist) {
      this.albumArtist = albumArtist;
    }

    // toString method
    @Override
    public String toString() {
      return albumName + ", " + albumArtist + ", " + numberOfSongs;
    }
  }

  public static void main(String[] args) {
    // Create an ArrayList of Album objects "albums1"
    ArrayList<Album> albums1 = new ArrayList<>();
    
    // Adding classic 90s and 00s hip-hop albums
    albums1.add(new Album("The Marshall Mathers LP", 18, "Eminem"));
    albums1.add(new Album("All Eyez on Me", 27, "2Pac"));
    albums1.add(new Album("The Blueprint", 13, "Jay-Z"));
    albums1.add(new Album("Ready to Die", 17, "The Notorious B.I.G."));
    albums1.add(new Album("The Chronic", 16, "Dr. Dre"));
    
    System.out.println("\n\nAll albums from albums1:\n");

    // Print all albums
    for (Album album : albums1) {
      System.out.println(album);
    }

    System.out.println("\n\nSwapped albums at index 0 and 1:\n");

    // Swap albums at index 0 and 1
    Album temp = albums1.get(0);
    albums1.set(0, albums1.get(1));
    albums1.set(1, temp);

    // Print albums after swapping
    for (Album album : albums1) {
        System.out.println(album);
    }

    // Create an ArrayList of Album objects "albums2"
    ArrayList<Album> albums2 = new ArrayList<>();

    // Create temporary ArrayList with pop albums
    ArrayList<Album> tempPopAlbums = new ArrayList<>();
    tempPopAlbums.add(new Album("...Baby One More Time", 11, "Britney Spears"));
    tempPopAlbums.add(new Album("No Strings Attached", 12, "NSYNC"));
    tempPopAlbums.add(new Album("Millennium", 12, "Backstreet Boys"));
    tempPopAlbums.add(new Album("Pure Shores: The Very Best of All Saints", 36, "All Saints"));
    tempPopAlbums.add(new Album("Celebrity", 13, "NSYNC"));

    // Add all pop albums to albums2
    albums2.addAll(tempPopAlbums);

    System.out.println("\n\nAll albums from albums2:\n");

    // Print all albums
    for (Album album : albums2) {
      System.out.println(album);
    }

    // Add all albums from albums1 to albums2
    albums2.addAll(albums1);

    // Add 2 albums to albums2
    albums2.add(new Album("Oops!... I Did It Again", 16, "Britney Spears"));
    albums2.add(new Album("Dark Side of the Moon", 9, "Pink Floyd"));

    // Sort albums alphabetically with a lambda expression
    Collections.sort(albums2, (a1, a2) -> a1.getAlbumName().compareToIgnoreCase(a2.getAlbumName()));

    // Print sorted albums
    System.out.println("\n\nAlbums sorted alphabetically:\n");
    for (Album album : albums2) {
      System.out.println(album);
    }

    // Find and print the index of "Dark Side of the Moon"
    int foundIndex = -1;
    for (int i = 0; i < albums2.size(); i++) {
      if (albums2.get(i).getAlbumName().equals("Dark Side of the Moon")) {
        foundIndex = i;
        break;
      }
    }

    System.out.println("\n\nIndex of 'Dark Side of the Moon': " + foundIndex);
  }
}
