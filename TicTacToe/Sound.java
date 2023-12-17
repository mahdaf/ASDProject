package TicTacToe;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #3
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */
import javax.sound.sampled.*;
import java.io.File;
class Sound {

    private Clip clip;
    AudioInputStream sound;

    public Sound(String soundFile) throws Exception {
            File sc = new File(soundFile);
            sound = AudioSystem.getAudioInputStream(sc);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }

    public void play() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
        clip.close();
    }
}