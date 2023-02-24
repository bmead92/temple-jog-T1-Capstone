package com.game.templejog;

import com.game.templejog.client.Main;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Sound {
    private static Clip clip;

    /* Handles the background theme music */
    public static void themeSound(String file) {
        try {
            InputStream landingSound = Main.class.getClassLoader().getResourceAsStream(file);
            InputStream buffer = new BufferedInputStream(landingSound);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(buffer);
            clip = AudioSystem.getClip();
            clip.addLineListener(event -> {
                if (event.getType().equals(LineEvent.Type.CLOSE)) {
                    clip.close();
                }
            });
            clip.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Unsupported audio file format.");
        } catch (IOException e) {
            System.out.println("Error: Could not read audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Could not play audio clip.");
        } catch (IllegalArgumentException ignored) {
        }
    }

    public static String turningSound(String noun, Game game) {
        if (noun.isEmpty()) {
            return InvalidNounInput.BAD_SOUND.getWarning();
        } else if (noun.equalsIgnoreCase("on")) {
            game.setPlaySound(true);
            Sound.themeSound(game.getCurrentRoom().getSound());
        } else if (noun.equalsIgnoreCase("off")) {
            Sound.stopSound();
            game.setPlaySound(false);
        }
        return "Turning sound " + noun;
    }

    public static void gameSound(Scanner scanner, Game game) {
        System.out.println(UserInput.TURN_MUSIC.getUserPrompt());
        String musicOn = scanner.nextLine();
        if (musicOn.equalsIgnoreCase("y")) {
            game.setPlaySound(true);
            Sound.themeSound("sounds/background_music.wav");
        } else {
            game.setPlaySound(false);
        }
    }

    public static void stopSound() {
        if (clip != null) {
            clip.stop();
        }
    }

    public static void ending(String filePath) {
        try {
            stopSound();
            InputStream sound = Main.class.getClassLoader().getResourceAsStream(filePath);
            InputStream buffer = new BufferedInputStream(sound);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(buffer);
            clip = AudioSystem.getClip();
            clip.addLineListener(event -> {
                if (event.getType().equals(LineEvent.Type.CLOSE)) {
                    clip.close();
                }
            });
            clip.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
            clip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Unsupported audio file format.");
        } catch (IOException e) {
            System.out.println("Error: Could not read audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Could not play audio clip.");
        } catch (IllegalArgumentException ignored) {
        }
    }

    public static Clip getClip() {
        return Sound.clip;
    }

    public static void setClip(Clip clip) {
        Sound.clip = clip;
    }
}
