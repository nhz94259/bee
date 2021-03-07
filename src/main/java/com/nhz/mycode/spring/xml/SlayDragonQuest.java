package com.nhz.mycode.spring.xml;

import java.io.PrintStream;

public class SlayDragonQuest  implements xmlSpringApplication.Quest {

    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}