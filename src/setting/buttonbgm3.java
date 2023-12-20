package setting;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class buttonbgm3 {
    AudioInputStream audioInputStream;
    Clip clip;

    public buttonbgm3() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // 使用完整的文件路径
        File file=new File("/Users/ylers/Desktop/JAVAproject/src/setting/moving-cursor-5.wav");
        audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        //clip.loop(Clip);
        clip.start();
    }
}
