package com.example.spring.data.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "member_hired_books")
public class MemberHired {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberIdCard;

    private String bookName;

    private Long bookId;

    private Long quantity;

    public MemberHired() {
    }

    public MemberHired(String memberIdCard, String bookName, Long bookId, Long quantity) {

        this.memberIdCard = memberIdCard;
        this.bookName = bookName;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberIdCard() {
        return memberIdCard;
    }

    public void setMemberIdCard(String memberIdCard) {
        this.memberIdCard = memberIdCard;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
