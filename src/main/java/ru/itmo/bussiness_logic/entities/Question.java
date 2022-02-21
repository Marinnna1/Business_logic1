package ru.itmo.bussiness_logic.entities;

import lombok.Data;
import ru.itmo.bussiness_logic.enums.Tag;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "questions", schema = "testdb")
@Data
public class Question implements Serializable {
    public Question() {
    }

    public Question(int creatorId, String head, String body, boolean evaluated, Tag tag) {
        this.creatorId = creatorId;
        this.head = head;
        this.body = body;
        this.evaluated = evaluated;
        this.tag = tag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name="head")
    private String head;

    @Column(name="body")
    private String body;

    @Column(name = "evaluated")
    private Boolean evaluated;

    @Column(name = "tag")
    @Enumerated(EnumType.STRING)
    private Tag tag;

    public int getId() {
        return id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public Boolean isEvaluated() {
        return evaluated;
    }

    public Tag getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}

