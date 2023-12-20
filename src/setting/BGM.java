package setting;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGM {
    AudioInputStream audioInputStream;
    Clip clip;

    public BGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // 使用完整的文件路径
        File file=new File("/Users/ylers/Desktop/JAVAproject/src/setting/Haru - Call of Silence.wav");
        audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
    public void close(){
        if (clip != null && clip.isRunning()) { // 检查音频剪辑是否正在运行
            clip.close();
        }
    }
    public void start(){
        if (clip != null && clip.isRunning()) { // 检查音频剪辑是否正在运行
            clip.start();
        }
    }
}
