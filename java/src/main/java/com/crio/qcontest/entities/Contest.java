package com.crio.qcontest.entities;

import java.util.List;
import javax.management.RuntimeErrorException;

public class Contest {
    private final String title;
    private final Level level;
    private final User createdBy;
    private final List<Question> questions;
    private ContestStatus contestStatus;
    private final Long id;

    public Contest(String title, Level level, User createdBy, List<Question> questions, Long id) {
        this.title = title;
        this.level = level;
        this.createdBy = createdBy;
        validateQuestions(questions,level);
        this.questions = questions;
        this.contestStatus = ContestStatus.NOT_STARTED;
        this.id = id;
    }

    public Contest(String title, Level level, User createdBy, List<Question> questions)
    {
        this(title, level, createdBy, questions,null);
    }

    private void validateQuestions(List<Question> questions, Level level)
    {
        for(int i=0; i< questions.size(); i++)
        {
            Question q= questions.get(i);
            if(q.getLevel() != level)
            {
                throw new RuntimeException("Level of some questions doesn't match contest level.");
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public Level getLevel() {
        return level;
    }

    public User getCreator() {
        return createdBy;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public ContestStatus getContestStatus() {
        return contestStatus;
    }

    public Long getId() {
        return id;
    }

    public void endContest()
    {
        this.contestStatus= ContestStatus.ENDED;
    }

    @Override
    public String toString() {
        return "Contest [id=" + id + "]";
    }
}
