package problem.selected;

import java.util.HashSet;
import java.util.Set;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isInRepeatingPlaylist() {
        Set<String> seen = new HashSet<>();
        Song p = this;
        seen.add(p.name);
        while (p.nextSong != null){
            p = p.nextSong;
            if (seen.contains(p.name)) return true;
            seen.add(p.name);
        }
        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isInRepeatingPlaylist());
    }
}
