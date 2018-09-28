package com.vyas.pranav.javajokelibrary;

import java.util.Random;

public class Joker {

    public String getJoke(){
        Random r = new Random();
        int id = r.nextInt(10)+1;
        switch (id){
            case 1:
                return "PUPIL: \"Would you punish me for something I didn\'t do?\" \n" +
                        "TEACHER:\" Of course not.\" \n" +
                        "PUPIL: \"Good, because I haven\'t done my homework.\" ";

            case 2:
                return "Little Johnny: Teacher, can I go to the bathroom? \n" +
                        "Teacher: Little Johnny, MAY I go to the bathroom? \n" +
                        "Little Johnny: But I asked first! ";

            case 3:
                return "Teacher: Why are you late? \n" +
                        "Student: There was a man who lost a hundred dollar bill. \n" +
                        "Teacher: That's nice. Were you helping him look for it? \n" +
                        "Student: No. I was standing on it. ";

            case 4:
                return "Customer: Excuse me, but I saw your thumb in my soup when you were carrying it. \n" +
                        "Waitress: Oh, that's okay. The soup isn't hot. ";

            case 5:
                return "\"I was born in California.\" \n" +
                        "\"Which part?\" \n" +
                        "\"All of me.\"";

            case 6:
                return "Teacher: Did your father help your with your homework? \n" +
                        "Student: No, he did it all by himself.";

            case 7:
                return "In a restaurant:\n" +
                        "Customer: Waiter, waiter! There is a frog in my soup!!! \n" +
                        "Waiter: Sorry, sir. The fly is on vacation. ";

            case 8:
                return "Guest at a restaurant: \"I refuse to eat this roastbeef. Please call the manager! \"\n" +
                        "\n" +
                        "Waiter: \"That\'s no use. He won\'t eat it either.\"";
            case 9:
                return "Guest at a restaurant: \"I refuse to eat this roastbeef. Please call the manager! \"\n" +
                        "\n" +
                        "Waiter: \"That\'s no use. He won\'t eat it either.\"";

            case 10:
                return "Man: Hi, do you want to dance?\n" +
                        "Woman: Yeah, sure!\n" +
                        "Man: Great, go and dance, I want to talk to your pretty friend!";

            default:
                return "Default Joke";
        }
    }
}
