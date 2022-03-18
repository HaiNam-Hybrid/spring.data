package com.example.spring.data.model;

import javax.persistence.*;
import java.time.Instant;

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

    private Long unitPrice;

    private Long forfeit;

    private Instant startTimeHired;

    private Instant endTimeHired;

    public MemberHired() {
    }

    public MemberHired(String memberIdCard, String bookName, Long bookId, Long quantity,
                       Long unitPrice, Long forfeit, Instant startTimeHired, Instant endTimeHired) {
        this.memberIdCard = memberIdCard;
        this.bookName = bookName;
        this.bookId = bookId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.forfeit = forfeit;
        this.startTimeHired = startTimeHired;
        this.endTimeHired = endTimeHired;
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

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getForfeit() {
        return forfeit;
    }

    public void setForfeit(Long forfeit) {
        this.forfeit = forfeit;
    }

    public Instant getStartTimeHired() {
        return startTimeHired;
    }

    public void setStartTimeHired(Instant startTimeHired) {
        this.startTimeHired = startTimeHired;
    }

    public Instant getEndTimeHired() {
        return endTimeHired;
    }

    public void setEndTimeHired(Instant endTimeHired) {
        this.endTimeHired = endTimeHired;
    }
}
