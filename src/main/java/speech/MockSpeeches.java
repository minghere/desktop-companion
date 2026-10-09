package speech;

import java.util.List;
import java.util.Random;

public class MockSpeeches {

    // Contains all mock speech lines that the mascot can say.
    private final List<String> speeches = List.of(
            "Lam ty inovar",
            "Siuuuuuuuuuuuuuuuuuu!",
            "May tay roi",
            "Moi uong coc tra tu tay",
            "Cam on bau duc",
            "Tuyet doi dien anh",
            "Na na anh do mixi",
            "Cho xin hop kho ga de"
    );

    // Used to randomly select a speech line.
    private final Random random = new Random();

    /*
     * Returns one random speech line from the list.
     */
    public String getRandomSpeech() {
        int randomIndex = random.nextInt(speeches.size());

        return speeches.get(randomIndex);
    }
}