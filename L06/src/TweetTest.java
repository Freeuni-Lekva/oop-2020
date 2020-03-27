import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {

    public void accessFieldsDirectly() {
        Tweet t = new Tweet("justinbieber",
                "Thanks to all those beliebers out there inspiring me every day",
                new Date());
        t.author = "rbmllr";
    }

}