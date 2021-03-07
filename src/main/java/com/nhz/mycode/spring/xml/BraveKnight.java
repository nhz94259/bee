package com.nhz.mycode.spring.xml;

public class BraveKnight implements xmlSpringApplication.Knight {


    private xmlSpringApplication.Quest quest;

    public BraveKnight(xmlSpringApplication.Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}
